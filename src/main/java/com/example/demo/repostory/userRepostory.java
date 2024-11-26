package com.example.demo.repostory;

import com.example.demo.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepostory extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
