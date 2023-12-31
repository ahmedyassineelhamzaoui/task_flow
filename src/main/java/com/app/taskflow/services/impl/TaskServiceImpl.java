package com.app.taskflow.services.impl;

import com.app.taskflow.common.exception.custom.TaskTagsSizeException;
import com.app.taskflow.common.exception.custom.TaskTimeException;
import com.app.taskflow.mapper.TaskMapper;
import com.app.taskflow.models.dto.TaskDTO;
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
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    @Override
    public void addTask(TaskDTO taskDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateFormat.parse(formatDateToString(taskDTO.getStartDate()));
            dateFormat.parse(formatDateToString(taskDTO.getEndDate()));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid timestamp format. Please use 'yyyy-MM-dd'T'HH:mm:ss' format");
        }
        if(!isUserExist(taskDTO.getUser().getId())){
            throw new NoSuchElementException("User not found");
        }
        if(taskDTO.getStartDate().after(taskDTO.getEndDate())){
            throw new TaskTimeException("Start date must be before end date");
        }
        if(taskDTO.getTags().size() <2){
            throw new TaskTagsSizeException("Tags size must be greater than 1");
        }
        taskRepository.save(taskMapper.toEntity(taskDTO));

    }
    public boolean isUserExist(UUID id){
        return userRepository.existsById(id);
    }
    private static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
