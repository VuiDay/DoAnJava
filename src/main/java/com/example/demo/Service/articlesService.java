package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Articles;
import com.example.demo.repostory.articlesRepostory;

@Service
public class articlesService {
    @Autowired 
    public articlesRepostory repo;

   
    public Articles getDetail(Integer id) { 
        return repo.findArticleWithUserAndComments(id); 
    }

    
    public List<Articles> getOtherArticles(Integer currentId) {
        return repo.findOtherArticles(currentId); 
    }
    
   
    public List<Articles> getArticlesByCategoryId(int categoryId) {
        return repo.findArticlesByCategoryId(categoryId); 
    }
    
   
}

