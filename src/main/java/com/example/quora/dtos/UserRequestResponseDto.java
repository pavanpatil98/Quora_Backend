package com.example.quora.dtos;

import com.example.quora.model.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestResponseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String username;
    String emailId;
    String password;
}
