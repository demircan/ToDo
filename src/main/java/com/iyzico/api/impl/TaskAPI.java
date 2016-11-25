package com.iyzico.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.api.ITaskAPI;
import com.iyzico.dao.TaskDaoI;
import com.iyzico.domain.Task;

@Component
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
noRollbackFor = {EmptyResultDataAccessException.class}, rollbackFor = {Exception.class})
public class TaskAPI implements ITaskAPI {

	@Autowired
	private TaskDaoI taskDao; 

	@Override
	public void createTask(Task task) {
		taskDao.persist(task);
	}

	@Override
	public void updateTask(Task task) {
		taskDao.merge(task);
	}

	@Override
	public void deleteTaskById(Long taskId) {
		taskDao.delete(taskDao.findById(taskId));
	}

	@Override
	public List<Task> getAllTask() {
		return taskDao.findAll();
	}

	@Override
	public Task getTask(Long id) {
		return taskDao.findById(id);
	}

}
