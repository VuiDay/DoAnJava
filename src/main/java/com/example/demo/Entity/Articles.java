package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Articles")

public class Articles {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String image;
    private String created_at;
    
    @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comments> comments  = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "category_id")
    private Categories category;
    
    public Articles() {
    }

	public Articles(int id, String title, String content, String image, String created_at, List<Comments> comments,
			User user, Categories category) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.created_at = created_at;
		this.comments = comments;
		this.user = user;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}
}
