package com.iyzico;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iyzico.api.IProjectAPI;
import com.iyzico.domain.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTest {

	
	@Autowired
	private IProjectAPI projectAPI;
	
	public void TestCreateProject(){
		Project project = new Project();
		project.setTitle("testProject");
		projectAPI.createProject(project);
		
		Assert.assertNotNull(project.getId());
	}
}
