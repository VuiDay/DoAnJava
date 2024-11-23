package com.example.demo.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Categories;
import com.example.demo.Entity.Articles;

public interface categoriesRepostory extends JpaRepository<Categories, Integer> {
	@Query("SELECT c FROM Categories c LEFT JOIN FETCH c.articles")
    List<Categories> findAllWithArticles();
}
