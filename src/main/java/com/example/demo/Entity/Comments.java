package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Comments")

public class Comments {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int article_id;
    private int user_id;
    private String comment;
    
    public Comments() {
    } // No argument constructor is required by JPA

	public Comments(int id, int article_id, int user_id, String comment) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
