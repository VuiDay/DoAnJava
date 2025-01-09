package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Articles;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.Comments;
import com.example.demo.Entity.User;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.articlesService;
import com.example.demo.Service.categoriesService;
import com.example.demo.Service.userService;

import jakarta.servlet.http.HttpSession;


@Controller
public class articlesController {
	@Autowired
	articlesService service;
	CommentService commentService;
	categoriesService categories;
	
	
	@GetMapping("/articles/{id}")
    public String getArticles(@PathVariable Integer id, Model model) {
        Articles article = service.getDetail(id);

        if (article != null) {
            Categories category = article.getCategory();

            model.addAttribute("article", article);
            model.addAttribute("author", article.getUser());
            model.addAttribute("category", category != null ? category.getName() : "Unknown");
            model.addAttribute("comments", article.getComments() != null ? article.getComments() : new ArrayList<>());

            List<Articles> otherArticles = service.getOtherArticles(id);
            model.addAttribute("otherArticles", otherArticles);

            // Lấy tối đa 6 bài viết
            int limit = Math.min(otherArticles.size(), 6);
            List<Articles> limitedArticles = otherArticles.subList(0, limit);
            model.addAttribute("limitedArticles", limitedArticles);

            return "client/detailArticle";
        } else {
            return "redirect:/";
        }
    }
 }
