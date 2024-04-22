package com.example.quora.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "answerBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseModel{
    @Column
    String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    protected User answeringUser;

    @OneToMany(mappedBy = "commentedAnswer",cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(mappedBy = "likeAnswer",cascade = CascadeType.ALL)
    List<Likes> likes;

}
