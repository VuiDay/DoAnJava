package com.example.demo.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Entity.Categories;
import com.example.demo.Entity.User;
import com.example.demo.Service.categoriesService;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {
    @Autowired
    private categoriesService service;


    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpSession session) {
        List<Categories> listCategories = service.getlist();
        if (listCategories == null || listCategories.isEmpty()) {
            listCategories = new ArrayList<>(); 
        }
        model.addAttribute("data", listCategories);
        
        System.out.println("Data nè: " + listCategories);
        
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        
        if (loggedInUser != null) {
        	model.addAttribute("userId", loggedInUser.getId());
        	
            model.addAttribute("username", loggedInUser.getUsername());
            model.addAttribute("role", loggedInUser.getRole());
        } else {
            model.addAttribute("username", null);
        }
    }
    


}
