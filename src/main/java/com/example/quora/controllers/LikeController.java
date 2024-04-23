package com.example.quora.controllers;

import com.example.quora.adapters.ConvertLikeDtoToLike;
import com.example.quora.dtos.LikeRequestDto;
import com.example.quora.dtos.LikeResponseDto;
import com.example.quora.model.Likes;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {
    ConvertLikeDtoToLike convertLikeDtoToLike;

    public LikeController(ConvertLikeDtoToLike convertLikeDtoToLike){
        this.convertLikeDtoToLike = convertLikeDtoToLike;
    }
    @PostMapping()
    public ResponseEntity<?> addLike(@RequestBody LikeRequestDto likeRequestDto){
        try{
        Likes like = convertLikeDtoToLike.createLike(likeRequestDto);
        LikeResponseDto likeResponseDto = LikeResponseDto.builder()
                                            .id(like.getId())
                                            .createdAt(like.getCreatedAt())
                                            .updatedAt(like.getUpdatedAt())
                                            .answerId(like.getLikeAnswer().getId())
                                            .likesCount(like.getLikeCount())
                                            .build();

        return new ResponseEntity<>(likeResponseDto, HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
