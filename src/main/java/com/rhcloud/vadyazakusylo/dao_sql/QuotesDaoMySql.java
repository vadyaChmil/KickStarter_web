package com.rhcloud.vadyazakusylo.dao_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rhcloud.vadyazakusylo.dao.QuotesDao;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public class QuotesDaoMySql implements QuotesDao {
	Connection connection;

	public QuotesDaoMySql(Connection connection) {
		super();
		this.connection = connection;
	}

	public List<Quote> getQuotesList() throws SqlConnectionException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuotes());
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Quote> quotes = new ArrayList<Quote>();
			String quote;
			String autor;
			while (resultSet.next()) {
				quote = resultSet.getString("quotes.quote");
				autor = resultSet.getString("autors.autor");
				quotes.add(new Quote(quote, autor));
			}
			return quotes;
		} catch (SQLException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
	}

	private String selectQuotes() {
		StringBuilder sql = new StringBuilder();
		sql.append("select quotes.quote, autors.autor ");
		sql.append("from quotes natural join autors;");
		return sql.toString();
	}
}