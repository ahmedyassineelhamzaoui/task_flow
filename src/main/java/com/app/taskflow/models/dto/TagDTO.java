package com.app.taskflow.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {

    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Size(min=2,message = "tag must contains at least 2 characters")
    private String name;
}
