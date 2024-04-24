package com.example.quora.controllers;

import com.example.quora.adapters.ConvertLikeDtoToLike;
import com.example.quora.dtos.AnswerResponseDto;
import com.example.quora.dtos.CommentResponseDto;
import com.example.quora.dtos.LikeRequestDto;
import com.example.quora.dtos.LikeResponseDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Comment;
import com.example.quora.model.Likes;
import com.example.quora.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {
    ConvertLikeDtoToLike convertLikeDtoToLike;
    LikeService likeService;

    public LikeController(ConvertLikeDtoToLike convertLikeDtoToLike, LikeService likeService) {
        this.convertLikeDtoToLike = convertLikeDtoToLike;
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllLikes() {
        List<Likes> likes = this.likeService.getAllLikes();
        List<LikeResponseDto> list = new ArrayList<>();
        for(Likes liked : likes){
            LikeResponseDto likeResponse = LikeResponseDto.builder()
                    .likesCount(liked.getLikesCount())
                    .id(liked.getId())
                    .createdAt(liked.getCreatedAt())
                    .updatedAt(liked.getUpdatedAt())
                    .answerId(liked.getLikeAnswer().getId())
                    .build();
            list.add(likeResponse);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<?> getLikes(@PathVariable("answerId") Long likeId) {
        try {
            Optional<Likes> like = likeService.getLike(likeId);
            Likes liked = like.get();
            LikeResponseDto likeResponse = LikeResponseDto.builder()
                    .likesCount(liked.getLikesCount())
                    .id(liked.getId())
                    .createdAt(liked.getCreatedAt())
                    .updatedAt(liked.getUpdatedAt())
                    .answerId(liked.getLikeAnswer().getId())
                    .build();
            return new ResponseEntity<>(likeResponse, HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<?> addLike(@RequestBody LikeRequestDto likeRequestDto) {
        try {
            Likes like = convertLikeDtoToLike.createLike(likeRequestDto);
            LikeResponseDto likeResponseDto = LikeResponseDto.builder()
                    .id(like.getId())
                    .createdAt(like.getCreatedAt())
                    .updatedAt(like.getUpdatedAt())
                    .answerId(like.getLikeAnswer().getId())
                    .likesCount(like.getLikesCount())
                    .build();
            if (like.getLikesCount() == 1) return new ResponseEntity<>(likeResponseDto, HttpStatus.CREATED);
            else return new ResponseEntity<>(likeResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("likeId") Long likeId) {
        try {
            this.likeService.delete(likeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
