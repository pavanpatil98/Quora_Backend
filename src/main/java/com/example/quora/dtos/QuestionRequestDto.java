package com.example.quora.dtos;

import com.example.quora.model.Answer;
import com.example.quora.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {
    private Long userid;
    private String question;
}
