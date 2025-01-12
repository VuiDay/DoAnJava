package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Comments;
import com.example.demo.repostory.CommentRepository;

@Service
public class CommentService {

	@Autowired
    private CommentRepository commentsRepository;

    public void save(Comments comment) {
        commentsRepository.save(comment);
    }

    public List<Comments> findByArticleId(int articleId) {
        return commentsRepository.findByArticleId(articleId);
    }
    
    public void deleteCommentById(int id) {
        commentsRepository.deleteById(id);
    }
}
