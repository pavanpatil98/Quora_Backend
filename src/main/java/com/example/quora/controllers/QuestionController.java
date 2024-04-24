package com.example.quora.controllers;

import com.example.quora.adapters.ConvertQuestionDtoToQuestion;
import com.example.quora.dtos.QuestionRequestDto;
import com.example.quora.dtos.QuestionResponseDto;
import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    QuestionService questionService;
    ConvertQuestionDtoToQuestion convertQuestionDtoToQuestion;

    public QuestionController(QuestionService questionService, ConvertQuestionDtoToQuestion convertQuestionDtoToQuestion) {
        this.questionService = questionService;
        this.convertQuestionDtoToQuestion = convertQuestionDtoToQuestion;

    }

    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        List<Question> questions = this.questionService.getAllQuestions();
        List<QuestionResponseDto> list = new ArrayList<>();
        for(Question question : questions){
            QuestionResponseDto questionResponse = QuestionResponseDto.builder()
                    .id(question.getId())
                    .createdAt(question.getCreatedAt())
                    .updatedAt(question.getUpdatedAt())
                    .question(question.getQuestion())
                    .questioningUserId(question.getQuestioningUser().getId()).build();
            list.add(questionResponse);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestion(@PathVariable("questionId") Long questionId) {
        try {
            Optional<Question> questionOptional = questionService.getQuestion(questionId);
            Question question = questionOptional.get();
            QuestionResponseDto questionResponse = QuestionResponseDto.builder()
                    .id(question.getId())
                    .createdAt(question.getCreatedAt())
                    .updatedAt(question.getUpdatedAt())
                    .question(question.getQuestion())
                    .questioningUserId(question.getQuestioningUser().getId()).build();
            return new ResponseEntity<>(questionResponse, HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        try {
            Question question = convertQuestionDtoToQuestion.createQuestion(questionRequestDto);
            QuestionResponseDto questionResponse = QuestionResponseDto.builder()
                    .id(question.getId())
                    .createdAt(question.getCreatedAt())
                    .updatedAt(question.getUpdatedAt())
                    .question(question.getQuestion())
                    .questioningUserId(question.getQuestioningUser().getId()).build();
            return new ResponseEntity<>(questionResponse, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("questionId") Long questionId) {
        try {
            this.questionService.delete(questionId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
