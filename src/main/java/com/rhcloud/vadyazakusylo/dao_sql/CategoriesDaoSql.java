package com.rhcloud.vadyazakusylo.dao_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rhcloud.vadyazakusylo.dao.CategoriesDao;
import com.rhcloud.vadyazakusylo.entity.Category;
import com.rhcloud.vadyazakusylo.exception.DaoSQLException;

@Component
public class CategoriesDaoSql extends AbstractDao implements CategoriesDao {

	private final String SELECT_FROM_CATEGORIES = "select id, category from categories;";

	@Override
	public List<Category> getCategoriesList() {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CATEGORIES);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("category");
				categories.add(new Category(id, name));
			}
			return categories;
		} catch (SQLException e) {
			throw new DaoSQLException(
					"Problem with getting Categories. " + "Error " + e.getErrorCode() + " " + e.getSQLState());
		}
	}
}
