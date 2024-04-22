package com.example.quora.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long userId;
    private Long answerId;
    private Date createdAt;
    private Date updatedAt;
    private String comment;
}
