package com.iyzico.api;

import java.util.List;

import com.iyzico.domain.Task;
import com.iyzico.domain.User;

public interface ITaskAPI {

	public void createTask(Task task);

	public void updateTask(Task task);

	public void deleteTaskById(Long taskId);

	public List<Task> getAllTask();

	public Task getTask(Long id);
	
}
