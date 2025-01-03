package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Articles;
import com.example.demo.Service.articlesService;


@Controller
public class articlesController {
	@Autowired
	articlesService service;
	
	
	 @GetMapping("/articles/{id}")
    public String getArticles(@PathVariable Integer id, Model model) {
        Articles article = service.getDetail(id);
        if (article != null) {
            model.addAttribute("article", article);
            model.addAttribute("author", article.getUser());
            model.addAttribute("comments", article.getComments() != null ? article.getComments() : new ArrayList<>());
            
            List<Articles> otherArticles = service.getOtherArticles(id);
            model.addAttribute("otherArticles", otherArticles);
            
        	List<Articles> limitedArticles = otherArticles.size() > 6 ? otherArticles.subList(0, 6) : otherArticles;
        	model.addAttribute("limitedArticles", limitedArticles);
        	
            return "article";
        } else {
            return "redirect:/";
        }
    }

    
}
