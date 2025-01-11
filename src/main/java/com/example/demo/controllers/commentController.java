package com.example.demo.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Articles;
import com.example.demo.Entity.Comments;
import com.example.demo.Entity.User;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.articlesService;
import com.example.demo.Service.userService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/comments")
public class commentController {

    @Autowired
    private CommentService commentsService;
    
    @Autowired
    private articlesService articlesService;
    
    @Autowired
    private userService userService;
    
    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @PostMapping("/add")
    public String addComment(@RequestParam("comment") String commentText, 
                             @RequestParam("articleId") int articleId,
                             HttpSession session) {
        // Lấy bài viết
        Articles article = articlesService.findById(articleId);
        if (article == null) {
            throw new IllegalArgumentException("Bài viết không tồn tại!");
        }
        User currentUser = userService.getLoggedInUser(session);
        if(currentUser == null) {
        	return "redirect:/login";
        }
        System.out.println("Comment trước khi lưu: " + currentUser);
        
        // Tạo bình luận mới
        Comments comment = new Comments();
        comment.setComment(commentText);
        comment.setArticle(article);
        comment.setUser(currentUser);
        comment.setCreated_at(getCurrentDateTime());

        // Lưu bình luận
        commentsService.save(comment);

        // Redirect về trang chi tiết bài viết
        return "redirect:/articles/" + articleId;
    }
}
