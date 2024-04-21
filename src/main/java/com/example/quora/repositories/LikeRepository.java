package com.example.quora.repositories;

import com.example.quora.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository  extends JpaRepository<Likes,Long>{
}
