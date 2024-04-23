package com.example.quora.adapters;

import com.example.quora.dtos.LikeRequestDto;
import com.example.quora.model.Likes;

public interface ConvertLikeDtoToLike {
    public Likes createLike(LikeRequestDto likeRequestDto);
}
