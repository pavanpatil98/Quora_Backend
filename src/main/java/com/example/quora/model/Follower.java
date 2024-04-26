package com.example.quora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder(builderMethodName = "followerBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Follower extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    User follower;
}
