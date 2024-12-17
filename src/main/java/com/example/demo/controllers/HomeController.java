package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.Articles;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.User;
import com.example.demo.Service.articlesService;
import com.example.demo.Service.categoriesService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	categoriesService service;
	
	@Autowired
	articlesService articlesService;
	
	@GetMapping("/Home")
	public String getArticles(Model model, HttpSession session) {
		List<Categories> listCategories = service.getlist();
		model.addAttribute("data", listCategories);
		
		User loggedInUser  = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
	        model.addAttribute("username", loggedInUser.getUsername());
	    } else {
	        model.addAttribute("username", null);
	    }
		return "index";
	}
	
	 @GetMapping("/technology")
	    public String showTechnologyArticles(Model model) {
	     
	        List<Articles> technologyArticles = articlesService.getArticlesByCategoryId(1);
	        model.addAttribute("articles", technologyArticles);
	        return "technology";
	    }

	
    @GetMapping("/health")
    public String health(Model model) {	
    	List<Articles> businessArticles = articlesService.getArticlesByCategoryId(2);
        model.addAttribute("articles", businessArticles);
        return "health"; 
    }

    @GetMapping("/business")
    public String business(Model model) {
    	List<Articles> businessArticles = articlesService.getArticlesByCategoryId(5);
        model.addAttribute("articles", businessArticles);
        return "business"; 
    }

    @GetMapping("/sport")
    public String sport(Model model) {
    	 List<Articles> sportArticles = articlesService.getArticlesByCategoryId(3);
         model.addAttribute("articles", sportArticles);
         return "sport"; 
    }

    @GetMapping("/entertainment")
    public String entertainment(Model model) {
    	 List<Articles> entertainmentArticles = articlesService.getArticlesByCategoryId(4);
         model.addAttribute("articles", entertainmentArticles);
        return "entertainment"; 
    }
}
