package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.repostory.userRepostory;

import jakarta.servlet.http.HttpSession;

@Service
public class userService {
    @Autowired
    public userRepostory repo;

    // Lấy danh sách tất cả người dùng
    public List<User> getList() {
        return repo.findAll();
    }

    // Lưu người dùng mới hoặc cập nhật người dùng
    public void save(User u) {
        repo.save(u);
    }

    // Đăng nhập người dùng
    public boolean login(String username, String password, HttpSession session) {
        User user = repo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            return true;
        }
        return false;
    }

    // Lấy người dùng đã đăng nhập
    public User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    // Đăng xuất người dùng
    public void logout(HttpSession session) {
        session.invalidate();
    }

    // Tìm kiếm người dùng theo ID
    public Optional<User> findById(int id) {
        return repo.findById(id);
    }

    // Cập nhật thông tin người dùng
    public void updateUser(User user) {
        repo.save(user);
    }

    // Xóa người dùng theo ID
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
