package com.example.quora.controllers;

import com.example.quora.adapters.ConvertUserDtoToUser;
import com.example.quora.dtos.UserRequestDto;
import com.example.quora.model.User;
import com.example.quora.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private ConvertUserDtoToUser convertUserDtoToUser;

    public UserController(UserService userService,ConvertUserDtoToUser convertUserDtoToUser){
        this.userService = userService;
        this.convertUserDtoToUser = convertUserDtoToUser;
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userDto){
        try{
            User user = convertUserDtoToUser.createUser(userDto);
            userService.addUser(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId){
        try{
            Optional<User> user = userService.getUser(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (EntityNotFoundException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId){
        try{
            this.userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
