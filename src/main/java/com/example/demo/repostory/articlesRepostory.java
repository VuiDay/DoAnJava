package com.example.demo.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Articles;

public interface articlesRepostory extends JpaRepository<Articles, Integer> { 

    @Query("SELECT a FROM Articles a JOIN FETCH a.user LEFT JOIN FETCH a.comments WHERE a.id = :id")
    Articles findArticleWithUserAndComments(@Param("id") Integer id); 

    @Query("SELECT a FROM Articles a WHERE a.id != :currentId ORDER BY a.created_at DESC")
    List<Articles> findOtherArticles(@Param("currentId") Integer currentId); 

    
    @Query("SELECT a FROM Articles a WHERE a.category.id = :categoryId")
    List<Articles> findArticlesByCategoryId(@Param("categoryId") int categoryId); 
    
}
  