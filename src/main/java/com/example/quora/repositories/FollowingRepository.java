package com.example.quora.repositories;

import com.example.quora.model.Answer;
import com.example.quora.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following,Long> {
}
