package com.iyzico.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iyzico.api.IProjectAPI;
import com.iyzico.api.ITaskAPI;
import com.iyzico.api.IUserAPI;
import com.iyzico.domain.Project;
import com.iyzico.domain.Task;
import com.iyzico.domain.User;
import com.iyzico.security.SecurityService;

@Controller
public class TaskController {

	@Autowired
	private ITaskAPI taskAPI;

	@Autowired
	private IUserAPI userAPI;

	@Autowired
	private IProjectAPI projectAPI;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value={"/","/task-list"})
	public String taskPage(Model md) {

		User user = securityService.activeUser();
		md.addAttribute("tasklist",user.getUserTaskList());
		md.addAttribute("username",user.getUsername());
		return "task";
	}

	@RequestMapping(value="new-task")
	public String addNewTask(Model md){

		User user = securityService.activeUser();
		md.addAttribute("projectList",user.getUserProjectList());
		
		return "new-task";
	}

	@RequestMapping(value="edit-task",method= RequestMethod.GET)
	public String getEditTaskPage(@RequestParam long taskId,Model md){

		Task task = taskAPI.getTask(taskId);
		User user = securityService.activeUser();
		md.addAttribute("projectList",user.getUserProjectList());
		md.addAttribute("projectId",task.getProject().getId());
		md.addAttribute("task",task);

		return "edit-task";
	}


	@RequestMapping(value="/delete-task",method= RequestMethod.GET)
	public String deleteTask(@RequestParam(value="taskId") long taskId, RedirectAttributes rd){

		taskAPI.deleteTaskById(taskId);
		rd.addFlashAttribute("success",1);
		
		return "redirect:/task-list";
	}

	@RequestMapping(value="/create-task",method = {RequestMethod.GET, RequestMethod.POST}) 
	public String createNewTask(@RequestParam(required = false) String title,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) long projectId,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date todoTime){

		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setTodoTime(todoTime);
		/*If selected project is not default, add projectId to task */
		if( projectId > 0){
			Project project = projectAPI.getProjectById(projectId);
			task.setProject(project);
		}
		User user = securityService.activeUser();
		task.setUser(user);
		taskAPI.createTask(task);

		return "redirect:/task-list";
	}


	@RequestMapping(value="/update-task",method= RequestMethod.POST)
	public String updateTask(@RequestParam Long taskId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) long projectId,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date todoTime){

		Task task = taskAPI.getTask(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setTodoTime(todoTime);
		/*If selected project is not default, add projectId to task */
		if( projectId > 0){
			Project project = projectAPI.getProjectById(projectId);
			task.setProject(project);
		}
		else{
			task.setProject(null);
		}
		User user = securityService.activeUser();
		task.setUser(user);
		taskAPI.updateTask(task);

		return "redirect:/task-list";
	}
}
