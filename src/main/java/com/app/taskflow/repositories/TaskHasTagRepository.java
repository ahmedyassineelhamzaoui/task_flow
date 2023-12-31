package com.app.taskflow.repositories;

import com.app.taskflow.models.entity.TaskHasTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskHasTagRepository  extends JpaRepository<TaskHasTags, UUID> {
}
