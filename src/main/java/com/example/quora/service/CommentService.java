package com.example.quora.service;

import com.example.quora.model.Comment;
import com.example.quora.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment){
        return this.commentRepository.save(comment);
    }
}
