package com.example.quora.repositories;

import com.example.quora.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Following,Long> {
}
