package com.app.taskflow.models.dto;


import jakarta.validation.constraints.NotBlank;
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
    private String name;
}
