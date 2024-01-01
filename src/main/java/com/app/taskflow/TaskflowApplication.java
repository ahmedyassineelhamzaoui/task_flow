package com.app.taskflow;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@EnableScheduling
public class TaskflowApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TaskflowApplication.class, args);
    }




}
