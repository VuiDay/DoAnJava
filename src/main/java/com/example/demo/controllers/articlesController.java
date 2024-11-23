package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.Articles;
import com.example.demo.Service.articlesService;

@Controller
public class articlesController {
	@Autowired
	articlesService service;
	@GetMapping("/article/{id}")
	public String getArticles(Model model) {
		return "article";
	}
}
