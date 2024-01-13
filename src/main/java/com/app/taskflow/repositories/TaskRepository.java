package com.app.taskflow.repositories;

import com.app.taskflow.enums.TaskStatus;
import com.app.taskflow.models.dto.response.TaskResponse;
import com.app.taskflow.models.entity.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("select t from Task t where t.title like %?1% or  t.description like %?1%")
     List<Task> findAllBySearchName(String name);

}
