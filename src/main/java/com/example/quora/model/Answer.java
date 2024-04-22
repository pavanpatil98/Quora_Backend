package com.example.quora.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder(builderMethodName = "answerBuilder")
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
    @Fetch(FetchMode.SUBSELECT)
    List<Comment> comments;

    @OneToMany(mappedBy = "likeAnswer",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<Likes> likes;

}
