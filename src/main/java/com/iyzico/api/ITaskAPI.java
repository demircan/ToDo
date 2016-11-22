package com.iyzico.api;

import java.util.List;
import com.iyzico.domain.Task;

public interface ITaskAPI {

	public void createTask(Task task);

	public void updateTask(Task task);

	public void deleteTask(Task task);

	public List<Task> getAllTask();

	public Task getTask(Long id);

}
