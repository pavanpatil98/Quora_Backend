package com.example.quora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "followerBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Follower extends User{
    @ManyToOne(fetch = FetchType.LAZY)
    User follower;
}
