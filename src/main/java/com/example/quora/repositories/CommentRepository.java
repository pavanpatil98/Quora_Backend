package com.example.quora.repositories;

import com.example.quora.model.Comment;
import com.example.quora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long>{
}
