package com.programadorItaloBack.app.controller.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name="reque_costumers")
public class ReqCostumer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@NotEmpty
	@Size(min = 3, max = 50)
	@Column(name = "name", nullable = false)
	private String name;

	@NonNull
	@NotEmpty
	@Size(min = 8, max = 20)
	@Column(name = "phone", nullable = false)
	private String phone;

	@NonNull
	@NotEmpty
	@Email
	@Column(nullable = false)
	private String email;

	@NonNull
	@NotEmpty
	@Size(min = 30, max = 400)
	@Column(nullable = false)
	private String message;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	private boolean contacted;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public boolean getContacted() {
		return contacted;
	}

	public void setContacted(boolean contacted) {
		this.contacted = contacted;
	}



	private static final long serialVersionUID = 1L;
}
