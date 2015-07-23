package com.rhcloud.vadyazakusylo.servlets;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rhcloud.vadyazakusylo.dao.QuotesDao;
import com.rhcloud.vadyazakusylo.dao_sql.QuotesDaoMySql;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public abstract class HttpKickStarter extends HttpServlet {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	static final String CONNECTION = "connection";
	static final String QUOTE = "quote";
	static final String CATEGORIES = "categories";
	static final String CATEGORY_NAME = "category";
	static final String PROJECTS = "projects";
	static final String PROJECT_NAME = "project";
	static final String PROJECT = "project";
	static final String DONATIONS = "donations";
	static final String ERROR_MESSAGE = "errorMessage";

	static final String HOME_PAGE = "home.jsp";
	static final String CATEGORY_PAGE = "category.jsp";
	static final String PROJECT_PAGE = "project.jsp";
	static final String DONATION_PAGE = "donation.jsp";
	static final String ERROR_PAGE = "error.jsp";

	Connection getConnection(HttpServletRequest request) throws SqlConnectionException {
		Connection connection = (Connection) request.getSession().getAttribute(CONNECTION);
		if (connection == null) {
			try {
				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
				DataSource dataSource = (DataSource) context.getBean("dataSource");
				connection = dataSource.getConnection();
				request.getSession().setAttribute(CONNECTION, connection);
			} catch (SQLException e) {
				throw new SqlConnectionException("Problem with connecting to DataBase");
			}
		}
		return connection;
	}
	
	Quote getQuote(Connection connection) throws SqlConnectionException {
		try {
			QuotesDao quotesDao = new QuotesDaoMySql(connection);
			List<Quote> quotesList = quotesDao.getQuotesList();
			Quote quote = quotesList.get((int) Math.round((Math.random() * (quotesList.size() - 1))));
			return quote;
		} catch (SqlConnectionException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
		
	}
}
