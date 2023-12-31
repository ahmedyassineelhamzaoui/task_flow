package com.app.taskflow.services.impl;

import com.app.taskflow.common.exception.custom.TaskTagsSizeException;
import com.app.taskflow.common.exception.custom.TaskTimeException;
import com.app.taskflow.mapper.TaskMapper;
import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.TaskHasTags;
import com.app.taskflow.repositories.TagRepository;
import com.app.taskflow.repositories.TaskHasTagRepository;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.TaskService;
import com.app.taskflow.services.facade.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Override
    public void addTask(TaskDTO taskDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!isUserExist(taskDTO.getUser().getId())){
            throw new NoSuchElementException("User not found");
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
        Task task =  taskRepository.save(taskMapper.toEntity(taskDTO));
        List<TaskHasTags> taskHasTagsList = toTaskHasTagsList(taskDTO.getTags(), task);
        taskHasTagRepository.saveAll(taskHasTagsList);
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
}
