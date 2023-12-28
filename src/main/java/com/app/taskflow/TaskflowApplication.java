package com.app.taskflow;

import com.app.taskflow.models.entity.RoleTable;
import com.app.taskflow.models.entity.UserTable;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TaskflowApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TaskflowApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
