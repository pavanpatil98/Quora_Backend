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
@SuperBuilder(builderMethodName = "userBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseModel{
    @Column(nullable = false,unique = true)
    protected String username;

    @Column(nullable = false,unique = true)
    protected String emailId;

    @Column(nullable = false)
    protected String password;


    @OneToMany(mappedBy = "following",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<Following> followingList;

    @OneToMany(mappedBy = "follower",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<Follower> followerList;

    @OneToMany(mappedBy = "questioningUser",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<Question> questions;

    @OneToMany(mappedBy = "answeringUser",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<Answer> answers;

    @OneToMany(mappedBy = "commentingUser",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    List<Comment> comments;

}
