package com.example.quora.adapters;

import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.dtos.CommentRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Comment;
import com.example.quora.model.Question;
import com.example.quora.model.User;
import com.example.quora.service.AnswerService;
import com.example.quora.service.CommentService;
import com.example.quora.service.QuestionService;
import com.example.quora.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConvertCommentDtoToCommentImpl implements ConvertCommentDtoToComment {

    UserService userService;
    AnswerService answerService;
    CommentService commentService;

    public ConvertCommentDtoToCommentImpl(UserService userService,AnswerService answerService,CommentService commentService){
        this.userService = userService;
        this.answerService = answerService;
        this.commentService = commentService;
    }

    @Override
    public Comment createComment(CommentRequestDto commentRequestDto) {
        //check if user is valid
        Long userId = commentRequestDto.getUserId();
        Optional<User> user = userService.checkUser(userId);

        //check if answer is valid
        Long answerId = commentRequestDto.getAnswerId();
        Optional<Answer> answer = answerService.checkAnswer(answerId);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with id " + userId + " does not exist");
        } else if (answer.isEmpty()) {
            throw new EntityNotFoundException("Answer with " + answerId + " does not exist");
        } else {
            Comment comment = Comment.commentBuilder().comment(commentRequestDto.getComment()).commentedAnswer(answer.get()).commentingUser(user.get()).build();
            commentService.addComment(comment);
            return  comment;
        }
    }


}
