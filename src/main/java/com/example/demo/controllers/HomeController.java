package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping("/")
	public String getArticles(Model model, HttpSession session) {
		List<Categories> listCategories = service.getlist();
		model.addAttribute("data", listCategories);
		
		User loggedInUser  = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
	        model.addAttribute("username", loggedInUser.getUsername());
	        model.addAttribute("role", loggedInUser.getRole());
	    } else {
	        model.addAttribute("username", null);
	    }
		return "client/homePage";
	}

	 
	 @GetMapping("/category/{id}")
	    public String health(@PathVariable Integer id, Model model) {	
	    	List<Articles> businessArticles = articlesService.getArticlesByCategoryId(id);
	    	Categories category  = service.findById(id);
	    	model.addAttribute("category", category.getName());
	        model.addAttribute("articles", businessArticles);
	        return "client/archive"; 
	    }
}
