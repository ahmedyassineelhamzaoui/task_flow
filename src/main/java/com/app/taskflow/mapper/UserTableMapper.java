package com.app.taskflow.mapper;


import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.UserTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserTableMapper {

    UserTableDTO toDTO(UserTable userTable);

    UserTable toEntity(UserTableDTO userTableDTO);
}
