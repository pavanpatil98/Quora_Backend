package com.example.quora.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "followingBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Following extends User{
    @ManyToOne(fetch = FetchType.LAZY)
    User following;
}
