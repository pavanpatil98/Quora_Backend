package com.example.quora.service;

import com.example.quora.model.Answer;
import com.example.quora.model.Comment;
import com.example.quora.model.Likes;
import com.example.quora.repositories.CommentRepository;
import com.example.quora.repositories.LikeRepository;
import org.springframework.stereotype.Service;

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


}
