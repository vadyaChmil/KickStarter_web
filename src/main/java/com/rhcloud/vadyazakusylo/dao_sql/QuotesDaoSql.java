package com.rhcloud.vadyazakusylo.dao_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rhcloud.vadyazakusylo.dao.QuotesDao;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.DaoSQLException;

@Component
public class QuotesDaoSql extends AbstractDao implements QuotesDao {

	@Override
	public List<Quote> getQuotesList() {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuotes());
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Quote> quotes = new ArrayList<Quote>();
			String quote;
			String autor;
			while (resultSet.next()) {
				quote = resultSet.getString("quote.text");
				autor = resultSet.getString("autor.name");
				quotes.add(new Quote(quote, autor));
			}
			return quotes;
		} catch (SQLException e) {
			throw new DaoSQLException("Problem with getting Quote. "
					+ "Error " + e.getErrorCode() + " " + e.getSQLState());
		}
	}

	private String selectQuotes() {
		StringBuilder sql = new StringBuilder();
		sql.append("select quote.text, autor.name ");
		sql.append("from quote natural join autor;");
		return sql.toString();
	}
}
