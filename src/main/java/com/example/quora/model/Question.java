package com.example.quora.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder(builderMethodName = "questionBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseModel{
    @Column
    protected String question;

    @ManyToOne(fetch = FetchType.LAZY)
    protected User questioningUser;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    protected List<Answer> answers;
}
