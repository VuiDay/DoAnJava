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
        Categories category = article.getCategory(); 
        if (article != null) {
            model.addAttribute("article", article);
            model.addAttribute("author", article.getUser());
            model.addAttribute("category", category.getName());
            model.addAttribute("comments", article.getComments() != null ? article.getComments() : new ArrayList<>());
            
            List<Articles> otherArticles = service.getOtherArticles(id);
            model.addAttribute("otherArticles", otherArticles);
            
        	List<Articles> limitedArticles = otherArticles.size() > 6 ? otherArticles.subList(0, 6) : otherArticles;
        	model.addAttribute("limitedArticles", limitedArticles);
        	
            return "client/detailArticle";
        } else {
            return "redirect:/";
        }
    }

	 @PostMapping("/articles/{id}/comments")
	 public String addComment(@PathVariable Integer id, @RequestParam String content, HttpSession session) {
	     // Lấy user từ session
	     User loggedInUser = (User) session.getAttribute("loggedInUser");
	     if (loggedInUser == null) {
	         return "redirect:/login"; // Nếu người dùng chưa đăng nhập
	     }

	     // Tạo đối tượng Comment mới
	     Comments comment = new Comments();
	     comment.setUser_id(loggedInUser.getId()); // Lấy user_id từ session
	     comment.setComment(content);

	     // Lấy bài viết theo article_id và gán vào comment
	     Articles article = service.getDetail(id);
	     comment.setArticle(article);

	     // Thêm bình luận vào cơ sở dữ liệu
	     commentService.addComment(comment);

	     return "redirect:/articles/" + id; // Quay lại trang chi tiết bài viết sau khi thêm bình luận
	 }
 }
