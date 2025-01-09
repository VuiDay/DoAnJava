package com.example.demo.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
    
}
