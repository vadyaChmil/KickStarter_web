package com.rhcloud.vadyazakusylo.dao_sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {

	@Autowired
	private DataSource dataSource;

	Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
