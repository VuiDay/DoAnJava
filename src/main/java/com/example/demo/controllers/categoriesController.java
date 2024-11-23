package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.Categories;
import com.example.demo.Service.categoriesService;

@Controller
public class categoriesController {
	@Autowired
	categoriesService service;
	@GetMapping("/")
	public String getArticles(Model model) {
		List<Categories> listCategories = service.getlist();
		model.addAttribute("data", listCategories);
		return "index";
	}
}
