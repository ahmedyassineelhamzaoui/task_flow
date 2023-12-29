package com.app.taskflow.repositories;

import com.app.taskflow.models.entity.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<RoleTable, UUID> {

    Optional<RoleTable> findByAuthority(String authority);
}
