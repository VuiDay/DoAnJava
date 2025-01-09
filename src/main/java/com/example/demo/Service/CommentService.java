package com.example.demo.Service;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Comments;
import com.example.demo.repostory.CommentRepository;

@Service
public class CommentService {

	 @Autowired
	    private CommentRepository commentRepository;

	    public void addComment(Comments comment) {
	        commentRepository.save(comment);  
	    }
}
