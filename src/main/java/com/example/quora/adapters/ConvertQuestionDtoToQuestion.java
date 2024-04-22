package com.example.quora.adapters;

import com.example.quora.dtos.QuestionRequestDto;
import com.example.quora.model.Question;

public interface ConvertQuestionDtoToQuestion {
    public Question createQuestion(QuestionRequestDto questionRequestDto);
}
