package com.example.quora.service;

import com.example.quora.model.Answer;
import com.example.quora.repositories.AnswerRepository;
import com.example.quora.repositories.QuestionRepository;

public class AnswerService {
    AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer(Answer answer){
        return this.answerRepository.save(answer);
    }
}
