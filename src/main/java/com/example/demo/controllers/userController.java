package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.User;
import com.example.demo.Service.userService; 

import jakarta.servlet.http.HttpSession;
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
	
	@GetMapping("/register")
	public String register(Model model) {
		return "/auth/register";
	}
	
	@PostMapping("/user/create")
	public String create(User u) {
		service.save(u);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "/auth/login";
	}
	
	 @PostMapping("/login")
	    public String login(User user, HttpSession session, Model model) {
	        boolean isAuthenticated = service.login(user.getUsername(), user.getPassword(), session);
	        if (isAuthenticated) {
	            return "redirect:/Home"; 
	        } else {
	            model.addAttribute("error", "Invalid username or password!");
	            return "/auth/login"; 
	        }
	    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        service.logout(session); 
        return "redirect:/login";
    }
	
}
