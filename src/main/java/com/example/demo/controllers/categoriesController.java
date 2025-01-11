package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.Categories;
import com.example.demo.Entity.User;
import com.example.demo.Service.categoriesService;

import jakarta.servlet.http.HttpSession;

@Controller
public class categoriesController {
	@Autowired
	categoriesService service;
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
		return "client/detailArticle";
	}
}
