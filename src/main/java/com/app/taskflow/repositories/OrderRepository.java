package com.app.taskflow.repositories;

import com.app.taskflow.models.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Demand, UUID> {
}
