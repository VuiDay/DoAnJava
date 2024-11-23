package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Articles;
import com.example.demo.repostory.articlesRepostory;

@Service
public class articlesService {
	@Autowired
	public articlesRepostory repo;

	public List<Articles> getlist() {
		// TODO Auto-generated method stub
		List<Articles> list = repo.findAll();
		return list;
	}
	
}
