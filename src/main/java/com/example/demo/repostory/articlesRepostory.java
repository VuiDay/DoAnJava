package com.example.demo.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Articles;

public interface articlesRepostory extends JpaRepository<Articles, Integer>{
	@Query("SELECT a FROM Articles a JOIN FETCH a.user LEFT JOIN FETCH a.comments WHERE a.id = :id")
    Articles findArticleWithUserAndComments(@Param("id") Integer id);
}
