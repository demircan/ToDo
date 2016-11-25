package com.iyzico.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iyzico.api.IProjectAPI;
import com.iyzico.domain.Project;
import com.iyzico.domain.User;
import com.iyzico.security.SecurityService;

@Controller
public class ProjectController {

	@Autowired
	private IProjectAPI projectAPI;
	
	@Autowired
	private SecurityService securityService;

	@RequestMapping(value="new-project")
	public String addNewProject(){
		return "new-project";
	}

	@RequestMapping(value="/create-project",method = {RequestMethod.GET, RequestMethod.POST}) 
	public String createNewProject(@RequestParam(required = false) String title){

		Project project = new Project();
		project.setTitle(title);
		User user = securityService.activeUser();
		project.setUser(user);
		projectAPI.createProject(project);
		
		return "redirect:/task-list";
	}
}
