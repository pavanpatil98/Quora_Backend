package com.example.quora.service;

import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }
    @Transactional
    public Question addQuestion(Question question) throws EntityNotFoundException {
        return this.questionRepository.save(question);
    }

    public Optional<Question> checkQuestion(Long id){
        return this.questionRepository.findById(id);
    }
}
