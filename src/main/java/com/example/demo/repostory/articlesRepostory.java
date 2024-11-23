package com.example.demo.repostory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Articles;

public interface articlesRepostory extends JpaRepository<Articles, Integer>{
}
