package com.example.quora.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder(builderMethodName = "followingBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Following extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    User following;
}
