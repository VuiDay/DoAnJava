package com.example.demo.Entity;

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
    private String user_id;
    private String created_at;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "category_id")
    private Categories category;
    
    public Articles() {
    }

	public Articles(int id, String title, String content, String image, String user_id, String created_at,
			Categories category) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.user_id = user_id;
		this.created_at = created_at;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}
}
