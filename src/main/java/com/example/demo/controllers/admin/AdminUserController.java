package com.example.demo.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.userService;
import com.example.demo.Entity.User;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private userService userService;
     
    // READ - Hiển thị danh sách
    @GetMapping("")
    public String index(Model model) {
        List<User> users = userService.getList();
        model.addAttribute("data", users);
        return "admin/user/index";
    }

    // CREATE - Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/add";
    }

    // CREATE - Xử lý thêm mới
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/user";
    }
 // UPDATE - Hiển thị form edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        // Sửa lại phần này
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/admin/user?error=user-not-found";
        }
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    // UPDATE - Xử lý cập nhật
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute User user) {
        try {
            User existingUser = userService.findById(id).orElse(null);
            if (existingUser == null) {
                return "redirect:/admin/user?error=user-not-found";
            }
            
            // Giữ lại password cũ nếu không nhập mới
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            }
            
            userService.save(user);
            return "redirect:/admin/user?success=updated";
        } catch (Exception e) {
            return "redirect:/admin/user?error=update-failed";
        }
    }
    // DELETE - Xử lý xóa
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/admin/user";
    }
}
