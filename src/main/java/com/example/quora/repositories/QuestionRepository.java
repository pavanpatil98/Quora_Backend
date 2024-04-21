package com.example.quora.repositories;

import com.example.quora.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<Question,Long> {

}
