package com.app.taskflow.mapper;


import com.app.taskflow.models.dto.UserDTO;
import com.app.taskflow.models.entity.UserTable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserTable mapToUser(UserDTO userDTO){
       return modelMapper.map(userDTO,UserTable.class);
    }
    public UserDTO mapToDTO(UserTable user){
        return modelMapper.map(user,UserDTO.class);
    }
}
