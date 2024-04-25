package com.example.quora.controllers;

import com.example.quora.adapters.ConvertUserDtoToUser;
import com.example.quora.dtos.AnswerResponseDto;
import com.example.quora.dtos.QuestionResponseDto;
import com.example.quora.dtos.UserQuestionsRequestDto;
import com.example.quora.dtos.UserRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Likes;
import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private QuestionService questionService;
    private AnswerService answerService;
    private CommentService commentService;
    private LikeService likeService;
    private ConvertUserDtoToUser convertUserDtoToUser;

    public UserController(UserService userService,ConvertUserDtoToUser convertUserDtoToUser,QuestionService questionService,AnswerService answerService,CommentService commentService,LikeService likeService){
        this.userService = userService;
        this.convertUserDtoToUser = convertUserDtoToUser;
        this.questionService = questionService;
        this.answerService = answerService;
        this.commentService = commentService;
        this.likeService = likeService;
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

    @GetMapping("/questions")
    public ResponseEntity<?> getMultipleUserQuestions(@RequestBody UserQuestionsRequestDto userIds){
        try{
            List<Long> driversList = userIds.getDriverids();
            List<User> users = userService.getUsers(driversList);
            List<Question> questions = questionService.getUsersQuestions(users);

            List<QuestionResponseDto> list = new ArrayList<>();
            for(Question question : questions){
                QuestionResponseDto questionResponse = QuestionResponseDto.builder()
                        .id(question.getId())
                        .createdAt(question.getCreatedAt())
                        .updatedAt(question.getUpdatedAt())
                        .question(question.getQuestion())
                        .questioningUserId(question.getQuestioningUser().getId()).build();
                list.add(questionResponse);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch (EntityNotFoundException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/questions")
    public ResponseEntity<?> getUserQuestions(@PathVariable("userId") Long userId){
        try{
            Optional<User> user = userService.getUser(userId);
            List<Question> questions = questionService.getQuestions(user.get());
            List<QuestionResponseDto> list = new ArrayList<>();
            for(Question question : questions){
                QuestionResponseDto questionResponse = QuestionResponseDto.builder()
                        .id(question.getId())
                        .createdAt(question.getCreatedAt())
                        .updatedAt(question.getUpdatedAt())
                        .question(question.getQuestion())
                        .questioningUserId(question.getQuestioningUser().getId()).build();
                list.add(questionResponse);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch (EntityNotFoundException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/questions/{questionId}")
    public ResponseEntity<?> test(@PathVariable("userId") Long userId,@PathVariable("questionId") Long questionId){
        try{
            Optional<User> user = userService.getUser(userId);
            Optional<Question> question = questionService.getQuestion(questionId);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch (EntityNotFoundException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/questions/{questionId}/answers")
    public ResponseEntity<?> test1(@PathVariable("userId") Long userId,@PathVariable("questionId") Long questionId){
        try{
            Optional<User> user = userService.getUser(userId);
            if(!user.isEmpty()){
                Optional<Question> question = questionService.getQuestion(questionId);
                if(!question.isEmpty()){
                    List<Answer> answers = answerService.getAnswer(question.get());
                    List<AnswerResponseDto> list = new ArrayList<>();
                    for(Answer answer : answers){
                        System.out.println(answer.getAnswer());
                        AnswerResponseDto answerResponse = AnswerResponseDto.builder()
                                .id(answer.getId())
                                .createdAt(answer.getCreatedAt())
                                .updatedAt(answer.getUpdatedAt())
                                .answer(answer.getAnswer())
                                .userId(answer.getAnsweringUser().getId())
                                .questionId(answer.getQuestion().getId())
                                .build();
                        list.add(answerResponse);
                    }
                    return new ResponseEntity<>(list, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
                }
            }
            else{
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
        }
        catch (EntityNotFoundException exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/questions/{questionId}/answers/likes")
    public ResponseEntity<?> test2(@PathVariable("userId") Long userId,@PathVariable("questionId") Long questionId,@RequestParam(name = "top", required = false, defaultValue = "10") int limit){
        try{
            Optional<User> user = userService.getUser(userId);
            if(!user.isEmpty()){
                Optional<Question> question = questionService.getQuestion(questionId);
                if(!question.isEmpty()){
                    List<Answer> answers = answerService.getAnswer(question.get());
                    List<Answer> topLikesAnswer = likeService.getTopNLikes(limit,answers);
                    List<AnswerResponseDto> list = new ArrayList<>();
                    for(Answer answer : topLikesAnswer){
                        System.out.println(answer.getAnswer());
                        AnswerResponseDto answerResponse = AnswerResponseDto.builder()
                                .id(answer.getId())
                                .createdAt(answer.getCreatedAt())
                                .updatedAt(answer.getUpdatedAt())
                                .answer(answer.getAnswer())
                                .userId(answer.getAnsweringUser().getId())
                                .questionId(answer.getQuestion().getId())
                                .build();
                        list.add(answerResponse);
                    }
                    return new ResponseEntity<>(list, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
                }
            }
            else{
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
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
