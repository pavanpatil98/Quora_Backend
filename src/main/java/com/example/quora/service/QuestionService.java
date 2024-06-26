package com.example.quora.service;

import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void delete(Long id){
        Optional<Question> questionOptional = checkQuestion(id);
        if(questionOptional.isEmpty()){
            System.out.println("Question does not exist");
            throw new EntityNotFoundException("Question with provided id does not exist");
        }
        else{
            this.questionRepository.deleteById(id);
        }
    }
    public List<Question> getUsersQuestions(List<User> userIds){
        return this.questionRepository.findAllByQuestioningUserIn(userIds);
    }

    public List<Question> getQuestions(User user){
        return this.questionRepository.findByQuestioningUser(user);
    }

    public List<Question> getAllQuestions(){
        return this.questionRepository.findAll();
    }

    public Optional<Question> getQuestion(Long questionId){
        return this.questionRepository.findById(questionId);
    }

}
