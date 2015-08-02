package com.rhcloud.vadyazakusylo.dao_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rhcloud.vadyazakusylo.dao.ProjectsDao;
import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.exception.DaoSQLException;

@Component
public class ProjectsDaoSql extends AbstractDao implements ProjectsDao {

	@Override
	public List<Project> getProjectsList(int categoryId) {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(selectProjects());
			preparedStatement.setInt(1, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Project> projects = new ArrayList<Project>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("project");
				int needMoney = resultSet.getInt("need_money");
				int currentMoney = resultSet.getInt("current_money");
				int daysLeft = resultSet.getInt("days_left");
				String description = resultSet.getString("description");
				projects.add(new Project(id, name, needMoney, currentMoney, daysLeft, description));
			}
			return projects;
		} catch (SQLException e) {
			throw new DaoSQLException("Problem with getting Projects. "
					+ "Error " + e.getErrorCode() + " " + e.getSQLState());
		}
	}

	private String selectProjects() {
		StringBuilder sql = new StringBuilder();
		sql.append("select id, project, need_money, current_money, ");
		sql.append("days_left, description ");
		sql.append("from projects where id_category = ?;");
		return sql.toString();
	}
}
