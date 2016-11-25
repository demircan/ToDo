package com.iyzico.api;

import com.iyzico.domain.Project;
import com.iyzico.domain.Task;

public interface IProjectAPI {

	public void createProject(Project project);

	public Project getProjectById(Long id);
	
}
