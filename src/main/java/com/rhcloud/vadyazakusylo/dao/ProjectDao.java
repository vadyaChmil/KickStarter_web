package com.rhcloud.vadyazakusylo.dao;

import java.util.Map;

import com.rhcloud.vadyazakusylo.entity.Project;

public interface ProjectDao {

	Project getProject(int projectId);

	Map<Integer, String> getDonations(int projectId);
	
	void setCurrentMoney(int money, int projectId);

	void setQuestion(String question, int projectId);

}