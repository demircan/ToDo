package com.iyzico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iyzico.api.IProjectAPI;
import com.iyzico.api.IUserAPI;
import com.iyzico.domain.Project;
import com.iyzico.domain.User;
import com.iyzico.security.SecurityService;

@Controller
public class UserController {

	@Autowired
	private IUserAPI userAPI;

	@Autowired
	private IProjectAPI projectAPI;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value="/login-page")
	public String homePage() {
		return "login";
	}
	
	@RequestMapping(value="/403")
	public String accessDeniedPage() {
		return "error";
	}

	@RequestMapping(value="/register-page")
	public String registerPage() {
		return "register";
	}

	@RequestMapping(value="/create-user",method = {RequestMethod.GET, RequestMethod.POST})   // ,produces = MediaType.TEXT_HTML
	public String createNewUser(@RequestParam(required = false) String app_username,
			@RequestParam(required = false) String app_password,
			Model md){
		
		/* check username  exist or not, if exist then warn user */ 
		if(userAPI.checkExistUsername(app_username)){
			md.addAttribute("loginError", true);
			return "register";
		}		
		User user = new User();
		user.setUsername(app_username);
		user.setPasswordHash(new BCryptPasswordEncoder().encode(app_password));
		userAPI.saveUser(user);
		
		/* Automatic login after register */
		securityService.autologin(app_username, app_password);
		
		/*Add default project */
		Project project = new Project();
		project.setTitle("Default");
		project.setUser(user);
		projectAPI.createProject(project);

		return "redirect:/task-list";
	}

}
