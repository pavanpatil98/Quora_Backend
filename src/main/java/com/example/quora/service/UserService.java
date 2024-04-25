package com.example.quora.service;

import com.example.quora.model.*;
import com.example.quora.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Optional<User> getUser(Long id) throws EntityNotFoundException {
        Optional<User> userOptional = checkUser(id);

        if(userOptional.isEmpty()){
            System.out.println("User does not exist");
            throw new EntityNotFoundException("User with provided id does not exist");
        }
        return userOptional;
    }

    @Transactional
    public User addUser(User user) throws EntityNotFoundException {
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void delete(Long id){
        Optional<User> userOptional = checkUser(id);
        if(userOptional.isEmpty()){
            System.out.println("User does not exist");
            throw new EntityNotFoundException("User with provided id does not exist");
        }
        else{
            this.userRepository.deleteById(id);
        }
    }

    public Optional<User> checkUser(Long Id){
        return this.userRepository.findById(Id);
    }

    public User checkUserName(String userName,String emailId){
        return this.userRepository.findByUsernameAndEmailId(userName,emailId);
        //findAllByUserNameAndEmailId();
    }

    public List<User> getUsers(List<Long> userIds){
        return this.userRepository.findAllByIdIn(userIds);
    }







}
