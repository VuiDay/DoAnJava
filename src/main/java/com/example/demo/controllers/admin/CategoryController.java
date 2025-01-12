package com.example.demo.controllers.admin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;  
import com.example.demo.Entity.Categories;
import com.example.demo.Service.categoriesService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    categoriesService categoryService;

    @GetMapping("")
    public String index(Model model) {
        List<Categories> categories = categoryService.getlist();
        model.addAttribute("data", categories);
        return "admin/category/index";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Categories());
        return "admin/category/add";
    }
    
    @PostMapping("/add")
    public String addPost(@ModelAttribute Categories category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Categories category = categoryService.findById(id);
        if(category == null) {
            return "redirect:/admin/category";
        }
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/edit/{id}") 
    public String editPost(@PathVariable Integer id, @ModelAttribute Categories category) {
    Categories existingCategory = categoryService.findById(id);
    if (existingCategory != null) {
        existingCategory.setName(category.getName());
        categoryService.save(existingCategory);
    }
    return "redirect:/admin/category";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return "redirect:/admin/category";
    }
}


