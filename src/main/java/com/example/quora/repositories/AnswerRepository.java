package com.example.quora.repositories;

import com.example.quora.model.Answer;
import com.example.quora.model.Likes;
import com.example.quora.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findAllByQuestion(Question question);

    List<Answer> findAllByQuestion(Likes like);
}
