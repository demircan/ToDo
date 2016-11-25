package com.iyzico.dao.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import com.iyzico.dao.ProjectDaoI;
import com.iyzico.domain.Project;

@Component
public class ProjectDaoImpl extends CommonDaoImpl<Project, Long> implements ProjectDaoI {

	@Override
	public Boolean checkExistTitle(String title) {

		Project exampleInstance = new Project();
		exampleInstance.setTitle(title);
		List<Project> projectList = findByExample(exampleInstance);
		if (projectList.isEmpty())
			return true;
		else
			return false;
	}

}
