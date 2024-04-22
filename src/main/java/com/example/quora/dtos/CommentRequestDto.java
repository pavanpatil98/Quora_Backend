package com.example.quora.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private Long userId;
    private Long answerId;
    private  String comment;
}
