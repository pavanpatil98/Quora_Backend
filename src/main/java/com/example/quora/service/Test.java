package com.example.quora.service;

import com.example.quora.model.*;
import com.example.quora.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class Test /*implements CommandLineRunner */{
    /*
    AnswerRepository answerRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;
    QuestionRepository questionRepository;
    UserRepository userRepository;

    public Test(AnswerRepository answerRepository, CommentRepository commentRepository, LikeRepository likeRepository, QuestionRepository questionRepository, UserRepository userRepository){
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Likes like1 = Likes.likeBuilder().build();
        Likes like2 = Likes.likeBuilder().build();
        Likes like3 = Likes.likeBuilder().build();

        Comment comment1 = Comment.commentBuilder().comment("Nice Explanation for answer1").build();
        Comment comment2 = Comment.commentBuilder().comment("Thanks for explaining it in short for answer1").build();
        Comment comment3 = Comment.commentBuilder().comment("Need to refine your answer for answer2").build();

        Answer answer1 = Answer.answerBuilder().likes(new ArrayList<>(Arrays.asList(like1,like2))).comments(new ArrayList<>(Arrays.asList(comment1,comment2))).answer("Carbon dating, also known as radiocarbon dating, is a method used to determine the age of organic materials by measuring the decay of radioactive carbon-14 (^14C) isotopes.").build();
        Answer answer2 = Answer.answerBuilder().likes(new ArrayList<>(Arrays.asList(like3))).comments(new ArrayList<>(Arrays.asList(comment3))).answer(" It's widely used in archaeology and other fields to estimate the age of ancient artifacts, fossils, and organic remains.").build();

        Question question = Question.questionBuilder().question("What is the concept of carbon-dating?").answers(new ArrayList<>(Arrays.asList(answer1,answer2))).build();

        User pavanUser = User.userBuilder().username("Pavan").emailId("pavanpatil@gmail.com").password("abcd").questions(new ArrayList<>(Arrays.asList(question))).build();
        User saurUser = User.userBuilder().username("Saurabh").emailId("Saurabh@gmail.com").password("xyz").answers(new ArrayList<>(Arrays.asList(answer1))).comments(new ArrayList<>(Arrays.asList(comment1,comment2))).build();
        User shubUser = User.userBuilder().username("Shubham").emailId("Shubham@gmail.com").password("pqr").answers(new ArrayList<>(Arrays.asList(answer2))).comments(new ArrayList<>(Arrays.asList(comment3))).build();


        userRepository.save(pavanUser);
        userRepository.save(saurUser);
        userRepository.save(shubUser);

        //Likes likes = Likes.likeBuilder().likeAnswer("id");
    }*/
}
