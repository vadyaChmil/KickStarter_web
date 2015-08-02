package com.rhcloud.vadyazakusylo.dao;

import java.util.List;

import com.rhcloud.vadyazakusylo.entity.Project;

public interface ProjectsDao {

	List<Project> getProjectsList(int categoryId);

}