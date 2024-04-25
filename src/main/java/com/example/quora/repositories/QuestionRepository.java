package com.example.quora.repositories;

import com.example.quora.model.Question;
import com.example.quora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Long> {
    List<Question> findAllByQuestioningUserIn(List<User> user);

    List<Question> findByQuestioningUser(User user);
}
