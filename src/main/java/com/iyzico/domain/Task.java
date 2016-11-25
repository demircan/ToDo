package com.iyzico.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import com.iyzico.domain.User;

@SuppressWarnings("serial")
@Entity
@Table(name = "task")
public class Task extends BaseEntity {

	private String description;

	@NotNull
	private String title;

	@Column(name = "todo_time", columnDefinition="DATETIME")
	private Date todoTime;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getTodoTime() {
		return todoTime;
	}

	public void setTodoTime(Date todoTime) {
		this.todoTime = todoTime;
	}

}
