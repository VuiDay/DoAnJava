package com.example.demo.controllers.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Articles;
import com.example.demo.Entity.User;
import com.example.demo.Service.articlesService;
import com.example.demo.Service.categoriesService;
import com.example.demo.Service.userService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {
    @Autowired
    private articlesService articleService;
    @Autowired
    private categoriesService categoryService;
    @Autowired
    private userService userService;

    // Thêm method để lấy thời gian hiện tại
    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @GetMapping("")
    public String index(Model model) {
        List<Articles> articles = articleService.getlist();
        model.addAttribute("data", articles);
        return "admin/articles/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("article", new Articles());
        model.addAttribute("categories", categoryService.getlist());
        model.addAttribute("users", userService.getList());
        return "admin/articles/add";
    }
    
    @GetMapping("/client-add")
    public String addClient(Model model, HttpSession session) {
    	User currentUser = userService.getLoggedInUser(session);
        if(currentUser == null) {
        	return "redirect:/login";
        }
        model.addAttribute("article", new Articles());
        model.addAttribute("categories", categoryService.getlist());
        model.addAttribute("users", userService.getList());
        return "client/articles/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Articles article, 
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        // Thêm thời gian tạo
        article.setCreated_at(getCurrentDateTime());

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads/");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Files.copy(imageFile.getInputStream(), 
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
                article.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        articleService.save(article);
        return "redirect:/admin/articles";
    }
    
    @PostMapping("/client-add")
    public String addPostClient(@ModelAttribute Articles article, 
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        // Thêm thời gian tạo
        article.setCreated_at(getCurrentDateTime());

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads/");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Files.copy(imageFile.getInputStream(), 
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
                article.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        articleService.save(article);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Articles article = articleService.getDetail(id);
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.getlist());
        model.addAttribute("users", userService.getList());
        return "admin/articles/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable("id") Integer id, 
            @ModelAttribute Articles article,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        Articles existingArticle = articleService.getDetail(id);
        
        // Cập nhật thời gian sửa
        article.setCreated_at(getCurrentDateTime());
        
        article.setId(id);
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads/");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                if (existingArticle.getImage() != null) {
                    Path oldImagePath = uploadPath.resolve(existingArticle.getImage().replace("/uploads/", ""));
                    Files.deleteIfExists(oldImagePath);
                }
                Files.copy(imageFile.getInputStream(), 
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
                article.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            article.setImage(existingArticle.getImage());
        }
        articleService.save(article);
        return "redirect:/admin/articles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Articles article = articleService.getDetail(id);
        if (article.getImage() != null) {
            try {
                Path imagePath = Paths.get("src/main/resources/static/uploads/")
                        .resolve(article.getImage().replace("/uploads/", ""));
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        articleService.deleteById(id);
        return "redirect:/admin/articles";
    }
}
