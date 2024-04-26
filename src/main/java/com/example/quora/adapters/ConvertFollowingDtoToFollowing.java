package com.example.quora.adapters;

import com.example.quora.dtos.FollowerRequestDto;
import com.example.quora.dtos.FollowingRequestDto;
import com.example.quora.model.Follower;

public interface ConvertFollowingDtoToFollowing {
    public Follower createFollowing(FollowingRequestDto followingRequestDto);
}
