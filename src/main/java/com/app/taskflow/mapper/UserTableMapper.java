package com.app.taskflow.mapper;


import com.app.taskflow.models.dto.UserTableDTO;
import com.app.taskflow.models.entity.UserTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserTableMapper {

    @Mapping(source = "email", target = "email")
    UserTableDTO toDTO(UserTable userTable);

    @Mapping(source = "email", target = "email")
    UserTable toEntity(UserTableDTO userTableDTO);
}
