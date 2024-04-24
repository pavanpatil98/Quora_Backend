package com.example.quora.service;

import com.example.quora.model.Answer;
import com.example.quora.model.Question;
import com.example.quora.repositories.AnswerRepository;
import com.example.quora.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void delete(Long id){
        Optional<Answer> answerOptional = checkAnswer(id);
        if(answerOptional.isEmpty()){
            System.out.println("Answer does not exist");
            throw new EntityNotFoundException("Answer with provided id does not exist");
        }
        else{
            this.answerRepository.deleteById(id);
        }
    }

    public List<Answer> getAllAnswers(){
        return this.answerRepository.findAll();
    }

    public Optional<Answer> getAnswer(Long questionId){
        return this.answerRepository.findById(questionId);
    }
}
