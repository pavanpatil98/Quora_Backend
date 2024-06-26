package com.example.quora.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDto {
    private Long userId;
    private Long questionId;
    private String answer;
}

