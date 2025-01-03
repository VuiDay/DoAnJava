package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Comments")

public class Comments {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private String comment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Articles article;
    
    public Comments() {
    } // No argument constructor is required by JPA

	public Comments(int id, int user_id, String comment, Articles article) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.comment = comment;
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	

}
