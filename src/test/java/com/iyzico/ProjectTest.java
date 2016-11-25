package com.iyzico;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iyzico.api.IProjectAPI;
import com.iyzico.api.IUserAPI;
import com.iyzico.dao.ProjectDaoI;
import com.iyzico.dao.UserDaoI;
import com.iyzico.domain.Project;
import com.iyzico.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTest {

	
	@Autowired
	private IProjectAPI projectAPI;
	
	@Autowired
	private ProjectDaoI projectDao;
	
	@Autowired
	private IUserAPI userAPI;
	
	private User user;
	
	private Project project;
	
	@Before
	public void initProject() {
		this.project = projectAPI.getProjectById(1L);
	}
	
	@Test
	public void TestCreateProject(){
		
		// prepare Project
		Project project = new Project();
		project.setTitle("testProject");
		
		// Create user 
		user = new User();
		user.setUsername("testName");
		user.setPasswordHash("test");
		userAPI.saveUser(user);
		
		// project and user connection
		project.setUser(user);
		projectAPI.createProject(project);
		
		Assert.assertNotNull(project.getId());
	}
	
	@Test
	public void TestUserProject(){
		Assert.assertNull(user.getUserProjectList());
	}
	
}
