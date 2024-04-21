package com.example.quora.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "userBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseModel{
    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String emailId;

    @Column(nullable = false)
    String password;


    @OneToMany(mappedBy = "following",cascade = CascadeType.ALL)
    List<Following> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "follower",cascade = CascadeType.ALL)
    List<Follower> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "questioningUser",cascade = CascadeType.ALL)
    List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "answeringUser",cascade = CascadeType.ALL)
    List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "commentingUser",cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

}
