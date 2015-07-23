package com.rhcloud.vadyazakusylo.entity;

import java.util.Map;

public class Project {
	private int id;
	private String name;
	private int needMoney;
	private int currentMoney;
	private int daysLeft;
	private String history;
	private String description;
	private String urlVideo;
	private Map<String, String> questions;
	private Map<Integer, String> donations;
	private int categoryId;

	public Project(int id, String name, int needMoney, int currentMoney, int daysLeft, String description) {
		this.id = id;
		this.name = name;
		this.needMoney = needMoney;
		this.currentMoney = currentMoney;
		this.daysLeft = daysLeft;
		this.description = description;
	}

	public Project(int id, String name, int needMoney, int currentMoney, int daysLeft, String history,
			String description, String urlVideo, Map<String, String> questions, int categoryId) {
		this.id = id;
		this.name = name;
		this.needMoney = needMoney;
		this.currentMoney = currentMoney;
		this.daysLeft = daysLeft;
		this.history = history;
		this.description = description;
		this.urlVideo = urlVideo;
		this.questions = questions;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNeedMoney() {
		return needMoney;
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public String getHistory() {
		return history;
	}

	public String getDescription() {
		return description;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public Map<String, String> getQuestions() {
		return questions;
	}

	public Map<Integer, String> getDonations() {
		return donations;
	}

	public int getCategoryId() {
		return categoryId;
	}
}