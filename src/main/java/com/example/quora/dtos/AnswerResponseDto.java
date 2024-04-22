package com.example.quora.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {
    private Long id;
    private Long userId;
    private Long questionId;
    private Date createdAt;
    private Date updatedAt;
    private String answer;
}
