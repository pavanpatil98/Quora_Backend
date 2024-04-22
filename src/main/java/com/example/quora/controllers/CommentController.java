package com.example.quora.controllers;

import com.example.quora.adapters.ConvertAnswerDtoToAnswerImpl;
import com.example.quora.adapters.ConvertCommentDtoToCommentImpl;
import com.example.quora.dtos.CommentRequestDto;
import com.example.quora.dtos.CommentResponseDto;
import com.example.quora.model.Comment;
import com.example.quora.service.AnswerService;
import com.example.quora.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    CommentService commentService;
    ConvertCommentDtoToCommentImpl convertCommentDtoToCommentImpl;

    public CommentController(CommentService commentService,ConvertCommentDtoToCommentImpl convertCommentDtoToCommentImpl){
        this.commentService = commentService;
        this.convertCommentDtoToCommentImpl = convertCommentDtoToCommentImpl;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentRequestDto commentRequestDto){
        try{
            Comment comment = convertCommentDtoToCommentImpl.createComment(commentRequestDto);
            CommentResponseDto commentResponse = CommentResponseDto.builder().id(comment.getId()).createdAt(comment.getCreatedAt()).updatedAt(comment.getUpdatedAt()).answerId(comment.getCommentedAnswer().getId()).userId(comment.getCommentingUser().getId()).build();
            return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    }
}
