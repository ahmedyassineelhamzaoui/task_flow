package com.app.taskflow;

import com.app.taskflow.common.exception.custom.TaskTagsSizeException;
import com.app.taskflow.mapper.TaskMapper;
import com.app.taskflow.mapper.UserTableMapper;
import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.TagRepository;
import com.app.taskflow.repositories.TaskHasTagRepository;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.impl.TaskServiceImpl;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.mockito.Mockito.*;


@SpringBootTest
public class AddTaskTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TaskHasTagRepository taskHasTagRepository;

    @Mock
    private UserTableMapper userTableMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testAddTaskSuccess() {
        // Arrange
        TaskDTO taskDTO = createTaskDTO();
        Task task = createTask();
        UserTable user = createUser();
        Authentication authentication = createAuthentication(user);

        when(taskRepository.save(Mockito.any())).thenReturn(task);
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);

        // Act
        taskService.addTask(taskDTO);

        // Assert
        Mockito.verify(taskRepository).save(Mockito.any());
        Mockito.verify(taskHasTagRepository).saveAll(Mockito.anyList());
    }

    @Test
    void testAddTaskTagsSizeException() {
        // Arrange
        TaskDTO taskDTO = createTaskDTO();
        taskDTO.setTags(Collections.singletonList(new TagDTO())); // Set tags size less than 2

        assertThrows(TaskTagsSizeException.class, () -> taskService.addTask(taskDTO));
    }


    private TaskDTO createTaskDTO() {
        // Create and return a TaskDTO
        return new TaskDTO();
    }

    private Task createTask() {
        // Create and return a Task entity
        return new Task();
    }

    private UserTable createUser() {
        // Create and return a UserTable entity
        return new UserTable();
    }

    private Authentication createAuthentication(UserTable user) {
        // Create and return an Authentication object with the given user as principal
        return new TestingAuthenticationToken(user, null);
    }


}
