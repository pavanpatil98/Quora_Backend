package com.example.quora.service;

import com.example.quora.model.Comment;
import com.example.quora.model.Likes;
import com.example.quora.repositories.CommentRepository;
import com.example.quora.repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    LikeRepository likeRepository;


    public LikeService(LikeRepository likeServiceRepository){
        this.likeRepository = likeRepository;
    }

    public Likes addLike(Likes likes){
        return this.likeRepository.save(likes);
    }

    public Long getLikesCount(Long answerId){
        return this.likeRepository.countById(answerId);
    }
}
