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
            Likes likes = null;
            Optional<Likes> likesOptional = likeService.getLike(answer.get());

            if(likesOptional.isEmpty()){
                long likesCount = 1;
                likes = Likes.likeBuilder().likeAnswer(answer.get()).likesCount(likesCount).build();
                System.out.println(likes.getLikesCount());
                likeService.addLike(likes);
            }
            else{
                Long likesCount = likesOptional.get().getLikesCount()+1;
                System.out.println(likesCount);
                likes = Likes.likeBuilder()
                        .id(likesOptional.get().getId())
                        .createdAt(likesOptional.get().getCreatedAt())
                        .updatedAt(likesOptional.get().getUpdatedAt())
                        .likeAnswer(answer.get())
                        .likesCount(likesCount)
                        .build();
                likeService.updateLikesCount(likesCount,answer.get());
            }
            return likes;
        }
    }
}
