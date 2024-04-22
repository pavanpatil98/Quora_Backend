package com.example.quora.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String username;
    String emailId;
    String password;
}
