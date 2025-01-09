package com.example.demo.Entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private int role;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Articles> articles;

    public User() {
    } // No argument constructor is required by JPA

	public User(int id, int role, String username, String email, String password, List<Articles> articles) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.email = email;
		this.password = password;
		this.articles = articles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	
}


