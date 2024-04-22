package com.example.quora.service;

import com.example.quora.model.Answer;
import com.example.quora.model.Question;
import com.example.quora.repositories.AnswerRepository;
import com.example.quora.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer(Answer answer){
        return this.answerRepository.save(answer);
    }

    public Optional<Answer> checkAnswer(Long id){
        return this.answerRepository.findById(id);
    }
}
