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
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

@Component
public class CategoriesDaoMySql extends AbstractDao implements CategoriesDao {

	@Override
	public List<Category> getCategoriesList() throws SqlConnectionException  {
		try (Connection connection = getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(selectCategories());
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("category");
				categories.add(new Category(id, name));
			}
			return categories;
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
	}

	private String selectCategories() {
		return "select id, category from categories;";
	}
}
