package com.iyzico.dao;

import com.iyzico.domain.Project;

public interface ProjectDaoI extends CommonDaoI<Project, Long> {

	public Boolean checkExistTitle(String title);

}
