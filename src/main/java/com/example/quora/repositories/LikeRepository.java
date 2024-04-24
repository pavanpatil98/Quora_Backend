package com.example.quora.repositories;

import com.example.quora.model.Answer;
import com.example.quora.model.Likes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository  extends JpaRepository<Likes,Long>{
    Long countById(Long Id);



    Optional<Likes> findByLikeAnswer(Answer likeAnswer);

    @Modifying
    @Transactional
    @Query("UPDATE Likes l SET l.likesCount = :newCount WHERE l.likeAnswer = :answer")
    void updateLikesByAnswer(@Param("newCount") Long likesCount, @Param("answer") Answer answer);

}
