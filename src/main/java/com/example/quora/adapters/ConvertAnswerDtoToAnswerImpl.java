package com.example.quora.adapters;

import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Question;
import com.example.quora.service.AnswerService;
import com.example.quora.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ConvertAnswerDtoToAnswerImpl implements ConvertAnswerDtoToAnswer{

    QuestionService questionService;
    AnswerService answerService;

    public ConvertAnswerDtoToAnswerImpl(QuestionService questionService,AnswerService answerService){
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public Answer createAnswer(AnswerRequestDto answerRequestDto)throws EntityNotFoundException{
        Long questionId = answerRequestDto.getQuestionId();
        Optional<Question> question = questionService.checkQuestion(questionId);
        if(question.isEmpty()){
            throw new EntityNotFoundException("Question with "+questionId+" does not exist");
        }
        else{
            return Answer.answerBuilder().answer(answerRequestDto.getAnswer()).build();
        }
    }
}
