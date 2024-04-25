package com.example.quora.dtos;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionsRequestDto {
    private ArrayList<Long> driverids;
}
