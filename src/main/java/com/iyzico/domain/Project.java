package com.iyzico.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity
@Table(name = "project")
public class Project extends BaseEntity {

	@NotNull
	@Column(name = "title")
	private String title;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
