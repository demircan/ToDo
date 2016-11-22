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
		// TODO Auto-generated method stub
		taskDao.persist(task);
	}

	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		taskDao.merge(task);
	}

	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub
		taskDao.delete(task);
	}

	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		return taskDao.findAll();
	}

	@Override
	public Task getTask(Long id) {
		// TODO Auto-generated method stub
		return taskDao.findById(id);
	}

}
