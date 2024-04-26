package com.example.quora.adapters;

import com.example.quora.dtos.CommentRequestDto;
import com.example.quora.dtos.FollowerRequestDto;
import com.example.quora.model.Comment;
import com.example.quora.model.Follower;

public interface ConvertFollowerDtoToFollower {
    public Follower createFollower(FollowerRequestDto commentRequestDto);
}
