package com.example.quora.dtos;

import com.example.quora.model.Answer;
import com.example.quora.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    protected String question;
    protected User questioningUser;
}
