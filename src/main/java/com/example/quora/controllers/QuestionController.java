package com.example.quora.controllers;

import com.example.quora.adapters.ConvertQuestionDtoToQuestion;
import com.example.quora.dtos.QuestionRequestDto;
import com.example.quora.dtos.QuestionResponseDto;
import com.example.quora.model.Question;
import com.example.quora.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    QuestionService questionService;
    ConvertQuestionDtoToQuestion convertQuestionDtoToQuestion;

    public QuestionController(QuestionService questionService,ConvertQuestionDtoToQuestion convertQuestionDtoToQuestion){
        this.questionService = questionService;
        this.convertQuestionDtoToQuestion = convertQuestionDtoToQuestion;

    }

    @PostMapping()
    public  ResponseEntity<?> addQuestion(@RequestBody QuestionRequestDto questionRequestDto){
        try{
            Question question = convertQuestionDtoToQuestion.createQuestion(questionRequestDto);
            QuestionResponseDto response = QuestionResponseDto.builder()
                    .id(question.getId())
                    .createdAt(question.getCreatedAt())
                    .updatedAt(question.getUpdatedAt())
                    .question(question.getQuestion())
                    .questioningUser(question.getQuestioningUser()).build();
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
