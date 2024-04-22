package com.example.quora.adapters;

import com.example.quora.service.AnswerService;
import com.example.quora.service.QuestionService;
import com.example.quora.service.UserService;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUtil {
    UserService userService;
    QuestionService questionService;
    AnswerService answerService;
}
