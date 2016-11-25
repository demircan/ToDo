package com.iyzico;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iyzico.api.ITaskAPI;
import com.iyzico.api.IUserAPI;
import com.iyzico.domain.Task;
import com.iyzico.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

	@Autowired 
	private ITaskAPI taskAPI;
	
	@Autowired 
	private IUserAPI userAPI;
	
	private User user;
	
	private Task task;
	
	@Before
	public void initTask() {
		this.task = taskAPI.getTask(1L);
	}
	
	@Test
	public void testCreateTask() {
		task = new Task();
		task.setTitle("TestTitle");
		task.setDescription("TestDescription");
		task.setTransactionTime(new Date());
		task.setUser(user);
		
		taskAPI.createTask(task);
	}
	
	@After
	public void testDeleteTask() {
		taskAPI.deleteTaskById(task.getId());
	}
	
}
