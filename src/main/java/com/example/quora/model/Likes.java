package com.example.quora.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder(builderMethodName = "likeBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Likes extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    Answer likeAnswer;

    Long likesCount;
}
