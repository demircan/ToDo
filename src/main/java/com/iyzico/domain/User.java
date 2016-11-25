package com.iyzico.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class User extends BaseEntity {

	@NotNull
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "password_hash")
	private String passwordHash;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Task> userTaskList;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Project> userProjectList;

	public User() {
		super();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

	public Set<Task> getUserTaskList() {
		return userTaskList;
	}

	public void setUserTaskList(Set<Task> userTaskList) {
		this.userTaskList = userTaskList;
	}

	public Set<Project> getUserProjectList() {
		return userProjectList;
	}

	public void setUserProjectList(Set<Project> userProjectList) {
		this.userProjectList = userProjectList;
	}
    
}
