package com.example.quora.controllers;

import com.example.quora.adapters.ConvertAnswerDtoToAnswerImpl;
import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.dtos.AnswerResponseDto;
import com.example.quora.dtos.QuestionResponseDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Question;
import com.example.quora.service.AnswerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/answer")
public class AnswerController {
    AnswerService answerService;
    ConvertAnswerDtoToAnswerImpl convertAnswerDtoToAnswerImpl;

    public AnswerController(AnswerService answerService, ConvertAnswerDtoToAnswerImpl convertAnswerDtoToAnswerImpl) {
        this.answerService = answerService;
        this.convertAnswerDtoToAnswerImpl = convertAnswerDtoToAnswerImpl;

    }

    @GetMapping
    public ResponseEntity<?> getAllAnswers() {
        List<Answer> answers = this.answerService.getAllAnswers();
        List<AnswerResponseDto> list = new ArrayList<>();
        for(Answer answer : answers){
            System.out.println(answer.getAnswer());
            AnswerResponseDto answerResponse = AnswerResponseDto.builder()
                    .id(answer.getId())
                    .createdAt(answer.getCreatedAt())
                    .updatedAt(answer.getUpdatedAt())
                    .answer(answer.getAnswer())
                    .userId(answer.getAnsweringUser().getId())
                    .questionId(answer.getQuestion().getId())
                    .build();
            list.add(answerResponse);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<?> getAnswer(@PathVariable("answerId") Long answerId) {
        try {
            Optional<Answer> answerOptional = answerService.getAnswer(answerId);
            Answer answer = answerOptional.get();
            AnswerResponseDto answerResponse = AnswerResponseDto.builder()
                    .id(answer.getId())
                    .createdAt(answer.getCreatedAt())
                    .updatedAt(answer.getUpdatedAt())
                    .answer(answer.getAnswer())
                    .userId(answer.getAnsweringUser().getId())
                    .questionId(answer.getQuestion().getId())
                    .build();
            return new ResponseEntity<>(answerResponse, HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addAnswer(@RequestBody AnswerRequestDto answerRequestDto) {
        try {
            Answer answer = convertAnswerDtoToAnswerImpl.createAnswer(answerRequestDto);
            AnswerResponseDto answerResponse = AnswerResponseDto.builder()
                    .id(answer.getId())
                    .createdAt(answer.getCreatedAt())
                    .updatedAt(answer.getUpdatedAt())
                    .answer(answer.getAnswer())
                    .userId(answer.getAnsweringUser().getId())
                    .questionId(answer.getQuestion().getId())
                    .build();
            return new ResponseEntity<>(answerResponse, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable("answerId") Long answerId) {
        try {
            this.answerService.delete(answerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
