package com.app.taskflow;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class TaskflowApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TaskflowApplication.class, args);
    }




}
