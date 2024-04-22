package com.example.quora.adapters;

import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.service.AnswerService;
import com.example.quora.service.QuestionService;
import com.example.quora.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ConvertAnswerDtoToAnswerImpl implements ConvertAnswerDtoToAnswer {

    QuestionService questionService;
    AnswerService answerService;
    UserService userService;

    public ConvertAnswerDtoToAnswerImpl(QuestionService questionService, AnswerService answerService, UserService userService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
    }

    @Override
    public Answer createAnswer(AnswerRequestDto answerRequestDto) throws EntityNotFoundException {
        //check if user is valid
        Long userId = answerRequestDto.getUserId();
        Optional<User> user = userService.checkUser(userId);

        //check if question is valid
        Long questionId = answerRequestDto.getQuestionId();
        Optional<Question> question = questionService.checkQuestion(questionId);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with " + userId + " does not exist");
        } else if (question.isEmpty()) {
            throw new EntityNotFoundException("Question with " + questionId + " does not exist");
        } else {
            Answer answer = Answer.answerBuilder().answer(answerRequestDto.getAnswer()).answeringUser(user.get()).question(question.get()).build();
            answerService.addAnswer(answer);
            return  answer;
        }
    }
}
