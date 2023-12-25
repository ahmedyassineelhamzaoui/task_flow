package com.app.taskflow.mapper;


import com.app.taskflow.models.dto.UserDTO;
import com.app.taskflow.models.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserMapper {

    private final ModelMapper modelMapper;

    public User mapToUser(UserDTO userDTO){
       return modelMapper.map(userDTO,User.class);
    }
    public UserDTO mapToDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }
}
