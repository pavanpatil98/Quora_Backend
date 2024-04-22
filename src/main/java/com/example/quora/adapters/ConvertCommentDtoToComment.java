package com.example.quora.adapters;

import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.dtos.CommentRequestDto;
import com.example.quora.model.Answer;
import com.example.quora.model.Comment;

public interface ConvertCommentDtoToComment {
    public Comment createComment(CommentRequestDto commentRequestDto);
}
