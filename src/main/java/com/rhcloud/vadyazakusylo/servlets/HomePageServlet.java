package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.dao.CategoriesDao;
import com.rhcloud.vadyazakusylo.dao_sql.CategoriesDaoMySql;
import com.rhcloud.vadyazakusylo.entity.Category;
import com.rhcloud.vadyazakusylo.entity.Quote;

public class HomePageServlet extends HttpKickStarter {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = -6656012281274171055L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection connection = (Connection) getConnection(request);

			Quote quote = getQuote(connection);
			request.setAttribute(QUOTE, quote);

			CategoriesDao categoriesDao = new CategoriesDaoMySql(connection);

			List<Category> categories = categoriesDao.getCategoriesList();
			request.setAttribute(CATEGORIES, categories);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}
