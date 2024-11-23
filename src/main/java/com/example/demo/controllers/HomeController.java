package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.persistence.*;

@RestController
public class HomeController {
	
	@GetMapping("/category")
	public ModelAndView cateGory() {
		ModelAndView modelAndView = new ModelAndView("category");
		return modelAndView;
	}
	
}
