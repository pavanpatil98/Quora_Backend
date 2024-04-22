package com.example.quora.adapters;

import com.example.quora.dtos.UserRequestDto;
import com.example.quora.model.User;

public interface ConvertUserDtoToUser {
    public User createUser(UserRequestDto userDTO);
}
