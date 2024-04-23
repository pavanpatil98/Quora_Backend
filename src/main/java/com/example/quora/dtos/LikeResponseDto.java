package com.example.quora.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Long answerId;
    private Long likesCount;
}
