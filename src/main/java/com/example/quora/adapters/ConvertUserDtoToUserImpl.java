package com.example.quora.adapters;

import com.example.quora.dtos.UserRequestResponseDto;
import com.example.quora.model.User;
import com.example.quora.repositories.UserRepository;
import com.example.quora.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConvertUserDtoToUserImpl implements ConvertUserDtoToUser{

    private UserService userService;

    public ConvertUserDtoToUserImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public User createUser(UserRequestResponseDto userDto) throws EntityExistsException {
        User user = userService.checkUserName(userDto.getUsername(), userDto.getEmailId());

        if(user != null){
            System.out.println(user.toString()+"----------------------------------------");
            throw new EntityExistsException("The user with "+ userDto.getId() +" already exists");
        }
        else{
          return  User.userBuilder().password(userDto.getPassword()).username(userDto.getUsername()).emailId(userDto.getEmailId()).build();
        }
    }
}
