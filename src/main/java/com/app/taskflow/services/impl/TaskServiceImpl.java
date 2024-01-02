package com.app.taskflow.services.impl;

import com.app.taskflow.common.exception.custom.TaskTagsSizeException;
import com.app.taskflow.common.exception.custom.TaskTimeException;
import com.app.taskflow.common.exception.custom.UserAssignTaskException;
import com.app.taskflow.enums.TaskStatus;
import com.app.taskflow.mapper.TaskMapper;
import com.app.taskflow.mapper.UserTableMapper;
import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.TaskHasTags;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.TagRepository;
import com.app.taskflow.repositories.TaskHasTagRepository;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.TaskService;
import com.app.taskflow.services.facade.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TaskHasTagRepository taskHasTagRepository;
    private final UserTableMapper userTableMapper;

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(taskDTO.getAssignedTo() != null){
            if(!isUserExist(taskDTO.getAssignedTo().getId())){
                throw new NoSuchElementException("User not found");
            }
        }

        try {
            dateFormat.parse(formatDateToString(taskDTO.getStartDate()));
            dateFormat.parse(formatDateToString(taskDTO.getEndDate()));
            long daysBetween = ChronoUnit.MINUTES.between(taskDTO.getStartDate().toInstant(), taskDTO.getEndDate().toInstant());
            if (daysBetween > 4320) {
                throw new IllegalArgumentException("The difference between the start date and end date should be less than or equal to 3 days");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid timestamp format. Please use 'yyyy-MM-dd'T'HH:mm:ss' format");
        }
        if(taskDTO.getStartDate().after(taskDTO.getEndDate())){
            throw new TaskTimeException("Start date must be before end date");
        }
        List<UUID> nonExistentTagIds = taskDTO.getTags().stream()
                .map(TagDTO::getId)
                .filter(tagId -> !isTagExist(tagId))
                .collect(Collectors.toList());

        if (!nonExistentTagIds.isEmpty()) {
            throw new NoSuchElementException("Tags not found with the following IDs: " + nonExistentTagIds);
        }
        if(taskDTO.getTags().size() <2){
            throw new TaskTagsSizeException("Tags size must be greater than 1");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserTable userTable = (UserTable) authentication.getPrincipal();

        if(userCanAssignTask(taskDTO,userTable)){
            taskDTO.setCreatedBy(userTableMapper.toDTO(userTable));
        }else{
            throw new UserAssignTaskException("You can't assign task to this user");
        }
        Task task =  taskRepository.save(taskMapper.toEntity(taskDTO));
        List<TaskHasTags> taskHasTagsList = toTaskHasTagsList(taskDTO.getTags(), task);
        taskHasTagRepository.saveAll(taskHasTagsList);
    }

    @Override
    public void assignTaskToUser(UUID id, UUID userId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
        UserTable userTable = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        if(userCanAssignTask(taskMapper.toDTO(task),userTable)){
            task.setAssignedTo(userTable);
            taskRepository.save(task);
        }else{
            throw new UserAssignTaskException("You can't assign task to this user");
        }
    }

    @Override
    public void changeTaskStatus(UUID id, String status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
        Date now = getDate(task);
        if(task.getEndDate().before(now)){
            throw new TaskTimeException("You can't change task status because task is overdue");
        }
        if(status.equals("COMPLETED")){
            task.setStatus(TaskStatus.COMPLETED);
        }else if(status.equals("IN_PROGRESS")){
            task.setStatus(TaskStatus.IN_PROGRESS);
        }else if(status.equals("TODO")){
            task.setStatus(TaskStatus.TODO);
        }else{
            throw new IllegalArgumentException("Invalid status");
        }
        taskRepository.save(task);
    }

    private static Date getDate(Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserTable user = (UserTable) authentication.getPrincipal();

        Date now = new Date();

        if(task.getAssignedTo() == null){
            throw new UserAssignTaskException("this task not assigned to any user to update it");
        }
        if(!task.getAssignedTo().getId().equals(user.getId())){
            throw new UserAssignTaskException("this task not assigned to you to update it");
        }
        if(task.getStartDate().after(now)){
            throw new TaskTimeException("You can't change task status because task not started yet");
        }
        return now;
    }

    public boolean isUserExist(UUID id){
        return userRepository.existsById(id);
    }
    private static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    public boolean isTagExist(UUID id){
        return tagRepository.existsById(id);
    }

    public List<TaskHasTags> toTaskHasTagsList(List<TagDTO> tagDTOs, Task task) {
        return tagDTOs.stream().map(tagDTO -> {
            TaskHasTags taskHasTags = new TaskHasTags();
            taskHasTags.setTask(task);
            taskHasTags.setTag(tagRepository.findById(tagDTO.getId()).orElseThrow(() -> new NoSuchElementException("Tag not found with ID: " + tagDTO.getId())));
            return taskHasTags;
        }).collect(Collectors.toList());
    }

    public  boolean  userCanAssignTask(TaskDTO taskDTO,UserTable userTable){
        for(RoleTable roleTable : userTable.getAuthorities()){
            if(roleTable.getAuthority().equals("ADMIN") || roleTable.getAuthority().equals("MANAGER")){
                return true;
            }
        }
        if(taskDTO.getAssignedTo() == null ){
            throw new UserAssignTaskException("You must assign task to you you can't take it by default");
        }
        if(taskDTO.getAssignedTo().getId().equals(userTable.getId())){
            return true;
        }
        return false;
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateOverdueTasks() {
        Date now = new Date();
        taskRepository.findAll().stream()
                .filter(task -> task.getEndDate().before(now) && !task.getStatus().equals(TaskStatus.NOT_DONE) && !task.getStatus().equals(TaskStatus.COMPLETED))
                .forEach(task -> {
                    task.setStatus(TaskStatus.NOT_DONE);
                    taskRepository.save(task);
                });
    }
}
