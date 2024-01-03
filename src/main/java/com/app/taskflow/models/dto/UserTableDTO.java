package com.app.taskflow.models.dto;


import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTableDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private Long modificationCredit;

    private Long deletionCredit;

}
