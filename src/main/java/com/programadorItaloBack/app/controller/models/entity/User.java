package com.programadorItaloBack.app.controller.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name="users")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NonNull
	@NotEmpty
	@Size(min = 6, max = 50)
	@Column(nullable=false)
	private String name;
	
	@NonNull
	@NotEmpty
	@Size(min = 6, max = 50)
	@Column(nullable=false, name="user_name")
	private String userName;
	
	@NonNull
	@NotEmpty
	@Column(nullable=false)
	@Email
	private String email;
	
	@NonNull
	@NotEmpty
	@Column(nullable=false)
	private String password;
	public long getId() {
		return id;
	}
	
	private String ROLE;
	
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getROLE() {
		return ROLE;
	}
	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
