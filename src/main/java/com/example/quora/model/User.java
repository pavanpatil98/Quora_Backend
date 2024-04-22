package com.example.quora.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    List<Following> followingList;

    @OneToMany(mappedBy = "follower",cascade = CascadeType.ALL)
    List<Follower> followerList;

    @OneToMany(mappedBy = "questioningUser",cascade = CascadeType.ALL)
    List<Question> questions;

    @OneToMany(mappedBy = "answeringUser",cascade = CascadeType.ALL)
    List<Answer> answers;

    @OneToMany(mappedBy = "commentingUser",cascade = CascadeType.ALL)
    List<Comment> comments;

}
