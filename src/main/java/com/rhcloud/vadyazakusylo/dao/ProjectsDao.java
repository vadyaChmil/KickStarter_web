package com.rhcloud.vadyazakusylo.dao;

import java.util.List;

import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public interface ProjectsDao {

	List<Project> getProjectsList(int categoryId) throws SqlConnectionException;

}