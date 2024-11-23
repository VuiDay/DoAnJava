package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.User;
import com.example.demo.Service.userService; 


@Controller
public class userController {
	@Autowired
	userService service;
	@GetMapping("/user")
	public String getUser(Model model) {
		List<User> listUser = service.getList();
		model.addAttribute("data", listUser);
		return "index";
	}
	@GetMapping("/user/form")
	public String formUser(Model model) {
		model.addAttribute("user", new User());
		return "FormCreate";
	}
	@PostMapping("/user/create")
	public String create(User u) {
		service.save(u);
		return "redirect:/user";
	}
	
	
}
