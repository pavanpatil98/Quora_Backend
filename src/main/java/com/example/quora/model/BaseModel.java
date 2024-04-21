package com.example.quora.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;



@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id // this annotation makes the id property a primary key of our table
    @GeneratedValue(strategy = GenerationType.TABLE) // Identity means auto_increment
    protected Long id;


    //@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate// this annotation tells spring that only handle it for object creation
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updatedAt;
}
