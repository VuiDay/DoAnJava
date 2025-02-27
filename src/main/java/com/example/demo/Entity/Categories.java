package com.example.demo.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Categories")

public class Categories {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @Column(name = "created_at")
    private Date created_at;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Articles> articles = new ArrayList<>(); // Initialize to avoid NullPointerException
    
    public Categories() {
    } // No argument constructor is required by JPA

	public Categories(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreated_at() {
        return created_at;
    }
	
	public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

	public void setCreated_at(Date created_at) {
		 this.created_at = created_at;
	}
}