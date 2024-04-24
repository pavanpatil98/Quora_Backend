package com.example.quora.service;

import com.example.quora.model.Answer;
import com.example.quora.model.Comment;
import com.example.quora.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment){
        return this.commentRepository.save(comment);
    }

    public Optional<Comment> checkComment(Long id){
        return this.commentRepository.findById(id);
    }

    public void delete(Long id){
        Optional<Comment> commentOptional = checkComment(id);
        if(commentOptional.isEmpty()){
            System.out.println("Comment does not exist");
            throw new EntityNotFoundException("Comment with provided id does not exist");
        }
        else{
            this.commentRepository.deleteById(id);
        }
    }

    public List<Comment> getAllComments(){
        return this.commentRepository.findAll();
    }

    public Optional<Comment> getComment(Long commentId){
        return this.commentRepository.findById(commentId);
    }
}
