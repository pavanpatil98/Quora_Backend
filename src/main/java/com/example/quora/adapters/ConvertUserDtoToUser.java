package com.example.quora.adapters;

import com.example.quora.dtos.UserRequestResponseDto;
import com.example.quora.model.User;

public interface ConvertUserDtoToUser {
    public User createUser(UserRequestResponseDto userDTO);
}
