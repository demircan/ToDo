package com.iyzico.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.api.IProjectAPI;
import com.iyzico.dao.ProjectDaoI;
import com.iyzico.domain.Project;

@Component
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
noRollbackFor = {EmptyResultDataAccessException.class}, rollbackFor = {Exception.class})
public class ProjectAPI implements IProjectAPI {

	@Autowired
	private ProjectDaoI projectDao;
	
	@Override
	public void createProject(Project project) {
		projectDao.persist(project);
	}

	@Override
	public Project getProjectById(Long id) {
		return projectDao.findById(id);
	}

}
