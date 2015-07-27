package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.entity.Category;
import com.rhcloud.vadyazakusylo.entity.Quote;

public class CategoriesServlet extends HttpKickStarter {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = -6656012281274171055L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Quote quote = getQuote();
			request.setAttribute(QUOTE, quote);

			List<Category> categories = categoriesDao.getCategoriesList();
			request.setAttribute(CATEGORIES, categories);
			request.getRequestDispatcher(CATEGORIES_PAGE).forward(request, response);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}
