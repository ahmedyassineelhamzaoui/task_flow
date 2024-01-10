package com.app.taskflow;

import com.app.taskflow.common.exception.custom.TaskTagsSizeException;
import com.app.taskflow.models.dto.TaskDTO;
import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.impl.TaskServiceImpl;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class taskServiceTest {

    @Mock
    private TaskRepository taskRepository;


    @InjectMocks
    private TaskServiceImpl taskService;



    @Test
    public void testAddTaskTagSizeException() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(new Date());
        taskDTO.setTags(new ArrayList<>());

        when(taskRepository.save(any(Task.class))).thenReturn(new Task());

        assertThrows(TaskTagsSizeException.class, () -> taskService.addTask(taskDTO));

        verify(taskRepository, never()).save(any(Task.class));
    }



    @Test
    public void testAddTaskTimeException() {

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(DateUtils.addDays(new Date(), 4));

        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(taskDTO));
    }


}
