package com.example.quora.adapters;

import com.example.quora.dtos.LikeRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Likes;
import com.example.quora.service.AnswerService;
import com.example.quora.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ConvertLikeDtoToLikeImpl implements ConvertLikeDtoToLike{

    AnswerService answerService;
    LikeService likeService;

    public ConvertLikeDtoToLikeImpl(AnswerService answerService,LikeService likeService){
        this.likeService = likeService;
        this.answerService = answerService;
    }

    @Override
    public Likes createLike(LikeRequestDto likeRequestDto)throws EntityNotFoundException {
        Long answerId = likeRequestDto.getAnswerId();
        Optional<Answer> answer = answerService.checkAnswer(answerId);

        if(answer.isEmpty()){
            throw new EntityNotFoundException("Answer with id " + answerId + " does not exist");
        }
        else{
            long likesCount = likeService.getLikesCount(answerId)+1;
            Likes likes = Likes.likeBuilder().likeAnswer(answer.get()).likeCount(likesCount).build();
            likeService.addLike(likes);
            return likes;
        }
    }
}
