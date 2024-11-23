package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Categories;
import com.example.demo.repostory.categoriesRepostory;

@Service
public class categoriesService {
	@Autowired
	categoriesRepostory repo;

	public List<Categories> getlist() {
		// TODO Auto-generated method stub
		List<Categories> list = repo.findAllWithArticles();
		return list;
	}
}
