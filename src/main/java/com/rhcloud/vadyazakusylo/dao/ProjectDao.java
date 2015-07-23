package com.rhcloud.vadyazakusylo.dao;

import java.util.Map;

import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public interface ProjectDao {

	Project getProject(int projectId) throws SqlConnectionException;

	Map<Integer, String> getDonatios(int projectId) throws SqlConnectionException;
	
	void setCurrentMoney(int money, int projectId) throws SqlConnectionException;

	void setQuestion(String question, int projectId) throws SqlConnectionException;

}