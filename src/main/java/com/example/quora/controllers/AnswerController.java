package com.example.quora.controllers;

import com.example.quora.adapters.ConvertAnswerDtoToAnswerImpl;
import com.example.quora.dtos.AnswerResponseDto;
import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/answer")
public class AnswerController {
    AnswerService answerService;
    ConvertAnswerDtoToAnswerImpl convertAnswerDtoToAnswerImpl;

    public AnswerController(AnswerService answerService,ConvertAnswerDtoToAnswerImpl convertAnswerDtoToAnswerImpl){
        this.answerService = answerService;
        this.convertAnswerDtoToAnswerImpl = convertAnswerDtoToAnswerImpl;

    }

    @PostMapping()
    public ResponseEntity<?> addQuestion(@RequestBody AnswerRequestDto answerRequestDto){
        try{
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
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
