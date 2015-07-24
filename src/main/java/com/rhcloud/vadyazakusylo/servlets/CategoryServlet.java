package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.dao.ProjectsDao;
import com.rhcloud.vadyazakusylo.dao_sql.ProjectsDaoMySql;
import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public class CategoryServlet extends HttpKickStarter {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection connection = getConnection(request);

			Quote quote = getQuote(connection);
			request.setAttribute(QUOTE, quote);

			ProjectsDao projectsDao = new ProjectsDaoMySql(connection);
			int categoryId = Integer.valueOf(request.getParameter("id"));
			List<Project> projects = projectsDao.getProjectsList(categoryId);
			request.setAttribute(PROJECTS, projects);
			request.getRequestDispatcher(CATEGORY_PAGE).forward(request, response);
		} catch (SqlConnectionException e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}
