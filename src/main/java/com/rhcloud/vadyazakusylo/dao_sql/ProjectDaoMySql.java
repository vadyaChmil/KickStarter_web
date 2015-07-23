package com.rhcloud.vadyazakusylo.dao_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import com.rhcloud.vadyazakusylo.dao.ProjectDao;
import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public class ProjectDaoMySql implements ProjectDao {
	private Connection connection;

	public ProjectDaoMySql(Connection connection) {
		this.connection = connection;
	}

	public Project getProject(int projectId) throws SqlConnectionException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectProject());
			preparedStatement.setInt(1, projectId);
			ResultSet resultSet = preparedStatement.executeQuery();
			Project project = null;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("project");
				int needMoney = resultSet.getInt("need_money");
				int currentMoney = resultSet.getInt("current_money");
				int daysLeft = resultSet.getInt("days_left");
				String history = resultSet.getString("history");
				String description = resultSet.getString("description");
				String urlVideo = resultSet.getString("url_video");
				Map<String, String> questions = getQuestionsMap(id);
				int categoryId = resultSet.getInt("id_category");
				project = new Project(id, name, needMoney, currentMoney, daysLeft, history, description, urlVideo,
						questions, categoryId);
			}
			return project;
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
	}

	private String selectProject() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id, project, need_money, current_money, ");
		sql.append("days_left, history, description, url_video, id_category ");
		sql.append("from projects where id = ?;");
		return sql.toString();
	}

	private Map<String, String> getQuestionsMap(int projectId) throws SqlConnectionException {
		Map<String, String> questions = new TreeMap<String, String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuestions());
			preparedStatement.setInt(1, projectId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet == null) {
				return null;
			}
			while (resultSet.next()) {
				String question = resultSet.getString("question");
				String answer = resultSet.getString("answer");
				questions.put(question, answer);
			}
			return questions;
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
	}

	private String selectQuestions() {
		StringBuilder sql = new StringBuilder();
		sql.append("select question, answer from questions ");
		sql.append("where id_project = ?;");
		return sql.toString();
	}

	public Map<Integer, String> getDonatios(int projectId) throws SqlConnectionException {
		Map<Integer, String> donations = new TreeMap<Integer, String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectDonations());
			preparedStatement.setInt(1, projectId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet == null) {
				return null;
			}
			while (resultSet.next()) {
				Integer money = resultSet.getInt("money");
				String pledge = resultSet.getString("pledge");
				donations.put(money, pledge);
			}
			return donations;
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
	}

	private String selectDonations() {
		StringBuilder sql = new StringBuilder();
		sql.append("select money, pledge, id_project from donations ");
		sql.append("where id_project = ?;");
		return sql.toString();
	}

	public double getCurrenMoney(int projectId) throws SqlConnectionException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectCurrentMoney());
			preparedStatement.setInt(1, projectId);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.getInt("current_money");
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase 003");
		}
	}

	private String selectCurrentMoney() {
		StringBuilder sql = new StringBuilder();
		sql.append("select current_money from projects ");
		sql.append("where id = ?;");
		return sql.toString();
	}

	public void setCurrentMoney(int money, int projectId) throws SqlConnectionException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateCurrentMoney());
			preparedStatement.setInt(1, money);
			preparedStatement.setInt(2, projectId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase  004");
		}
	}

	private String updateCurrentMoney() {
		StringBuilder sql = new StringBuilder();
		sql.append("update projects ");
		sql.append("set current_money = ? ");
		sql.append("where id = ?;");
		return sql.toString();
	}

	public void setQuestion(String question, int projectId) throws SqlConnectionException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuestion());
			preparedStatement.setString(1, question);
			preparedStatement.setInt(2, projectId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase 005");
		}
	}

	private String insertQuestion() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into questions ");
		sql.append("(question, id_project) ");
		sql.append("values (?, ?);");
		return sql.toString();
	}
}
