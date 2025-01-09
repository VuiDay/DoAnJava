package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Articles;
import com.example.demo.repostory.articlesRepostory;

@Service
public class articlesService {
    @Autowired 
    private articlesRepostory repo;

    // Lấy chi tiết bài viết theo ID
    public Articles getDetail(Integer id) { 
        return repo.findArticleWithUserAndComments(id); 
    }

    // Lấy các bài viết khác ngoại trừ bài viết hiện tại
    public List<Articles> getOtherArticles(Integer currentId) {
        return repo.findOtherArticles(currentId); 
    }

    // Lấy danh sách bài viết theo categoryId
    public List<Articles> getArticlesByCategoryId(int categoryId) {
        return repo.findArticlesByCategoryId(categoryId); 
    }

    // Lấy tất cả các bài viết
    public List<Articles> getlist() {
        return repo.findAll(); // Lấy tất cả các bài viết
    }

    // Lưu hoặc cập nhật bài viết
    public Articles save(Articles article) {
        return repo.save(article); // Lưu hoặc cập nhật bài viết
    }

    // Xóa bài viết theo ID
    public void deleteById(Integer id) {
        repo.deleteById(id); // Xóa bài viết
    }

    // Lấy các bài viết của một người dùng cụ thể
    public List<Articles> getArticlesByUserId(int userId) {
        return repo.findArticlesByCategoryId(userId); // Tìm các bài viết của người dùng cụ thể
    }
}
