package com.app.taskflow.models.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class TagDTO {

    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;
}
