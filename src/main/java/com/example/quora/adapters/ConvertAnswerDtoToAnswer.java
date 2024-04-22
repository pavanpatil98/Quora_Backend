package com.example.quora.adapters;

import com.example.quora.dtos.AnswerRequestDto;
import com.example.quora.dtos.QuestionRequestDto;
import com.example.quora.model.Answer;

public interface ConvertAnswerDtoToAnswer {
    public Answer createAnswer(AnswerRequestDto answerRequestDto);
}
