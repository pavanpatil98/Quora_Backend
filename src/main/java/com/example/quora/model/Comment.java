package com.example.quora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "commentBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel{
    @Column
    protected String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Answer commentedAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    protected User commentingUser;
}
