package com.example.quora.adapters;

import com.example.quora.dtos.QuestionRequestDto;
import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.service.QuestionService;
import com.example.quora.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConvertQuestionDtoToQuestionImpl implements ConvertQuestionDtoToQuestion{

    UserService userService;
    QuestionService questionService;

    public ConvertQuestionDtoToQuestionImpl(QuestionService questionService, UserService userService){
        this.questionService = questionService;
        this.userService = userService;
    }

    @Override
    public Question createQuestion(QuestionRequestDto questionRequestDto) throws EntityNotFoundException{
        Long id = questionRequestDto.getUserid();
        Optional<User> user = userService.checkUser(id);
        if(user.isEmpty()){
            throw new EntityNotFoundException("User with "+id+" does not exist");
        }
        else{
            Question question = Question.questionBuilder().question(questionRequestDto.getQuestion()).questioningUser(user.get()).build();
            questionService.addQuestion(question);
            return  question;
        }
    }
}
