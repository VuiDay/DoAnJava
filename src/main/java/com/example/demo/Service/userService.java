package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.repostory.userRepostory;

import jakarta.servlet.http.HttpSession;
@Service
public class userService {
	@Autowired
	public userRepostory repo;

	public List<User> getList() {
		List<User> list = repo.findAll();
		// TODO Auto-generated method stub
		return list;
	}

	public void save(User u) {
		// TODO Auto-generated method stub
		repo.save(u);
	}
	
	 public boolean login(String username, String password, HttpSession session) {
	
	        User user = repo.findByUsername(username);

	 
	        if (user != null && user.getPassword().equals(password)) {
	      
	            session.setAttribute("loggedInUser", user);
	            return true;
	        }
	        return false; 
	    }
	 
	 public User getLoggedInUser(HttpSession session) {
	        return (User) session.getAttribute("loggedInUser");
	    }

	 public void logout(HttpSession session) {
	        session.invalidate(); 
	    }
	
}
