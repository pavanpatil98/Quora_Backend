package com.example.quora.controllers;

import com.example.quora.adapters.ConvertCommentDtoToCommentImpl;
import com.example.quora.dtos.AnswerResponseDto;
import com.example.quora.dtos.CommentRequestDto;
import com.example.quora.dtos.CommentResponseDto;
import com.example.quora.model.Comment;
import com.example.quora.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    CommentService commentService;
    ConvertCommentDtoToCommentImpl convertCommentDtoToCommentImpl;

    public CommentController(CommentService commentService, ConvertCommentDtoToCommentImpl convertCommentDtoToCommentImpl) {
        this.commentService = commentService;
        this.convertCommentDtoToCommentImpl = convertCommentDtoToCommentImpl;
    }

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        List<Comment> comments = this.commentService.getAllComments();
        List<CommentResponseDto> list = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto commentResponse = CommentResponseDto.builder()
                    .id(comment.getId())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .answerId(comment.getCommentedAnswer()
                            .getId()).userId(comment.getCommentingUser().getId())
                    .comment(comment.getComment())
                    .build();
            list.add(commentResponse);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable("commentId") Long commentId) {
        try {
            Optional<Comment> commentOptional = commentService.getComment(commentId);
            Comment comment = commentOptional.get();
            CommentResponseDto commentResponse = CommentResponseDto.builder()
                    .id(comment.getId())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .answerId(comment.getCommentedAnswer()
                            .getId()).userId(comment.getCommentingUser().getId())
                    .comment(comment.getComment())
                    .build();
            return new ResponseEntity<>(commentResponse, HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentRequestDto commentRequestDto) {
        try {
            Comment comment = convertCommentDtoToCommentImpl.createComment(commentRequestDto);
            CommentResponseDto commentResponse = CommentResponseDto.builder()
                    .id(comment.getId())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .answerId(comment.getCommentedAnswer()
                            .getId()).userId(comment.getCommentingUser().getId())
                    .comment(comment.getComment())
                    .build();
            return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable("commentId") Long commentId) {
        try {
            this.commentService.delete(commentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
