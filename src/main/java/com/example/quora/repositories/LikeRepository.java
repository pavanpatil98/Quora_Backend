package com.example.quora.repositories;

import com.example.quora.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository  extends JpaRepository<Likes,Long>{
    Long countById(Long Id);
}
