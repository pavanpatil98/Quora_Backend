package com.example.quora.service;

import com.example.quora.model.Answer;
import com.example.quora.model.Comment;
import com.example.quora.model.Likes;
import com.example.quora.repositories.CommentRepository;
import com.example.quora.repositories.LikeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    LikeRepository likeRepository;


    public LikeService(LikeRepository likeRepository){
        this.likeRepository = likeRepository;
    }

    public Likes addLike(Likes likes){
        return this.likeRepository.save(likes);
    }

    public Optional<Likes> getLike(Answer answer){
       return this.likeRepository.findByLikeAnswer(answer);
    }

    public void updateLikesCount(Long likesCount, Answer answer){
        this.likeRepository.updateLikesByAnswer(likesCount,answer);
    }

    public Optional<Likes> checkLike(Long id){
        return this.likeRepository.findById(id);
    }

    public void delete(Long id){
        Optional<Likes> likeOptional = checkLike(id);
        if(likeOptional.isEmpty()){
            System.out.println("Like does not exist");
            throw new EntityNotFoundException("Like with provided id does not exist");
        }
        else{
            this.likeRepository.deleteById(id);
        }
    }

    public List<Likes> getAllLikes(){
        return this.likeRepository.findAll();
    }

    public Optional<Likes> getLike(Long likeId){
        return this.likeRepository.findById(likeId);
    }



}
