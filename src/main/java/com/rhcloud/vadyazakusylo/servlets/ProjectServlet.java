package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.dao.ProjectDao;
import com.rhcloud.vadyazakusylo.dao.QuotesDao;
import com.rhcloud.vadyazakusylo.dao_sql.ProjectDaoMySql;
import com.rhcloud.vadyazakusylo.dao_sql.QuotesDaoMySql;
import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public class ProjectServlet extends HttpKickStarter {

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
			
			ProjectDao projectDao = new ProjectDaoMySql(connection);
			
			String text = request.getParameter("text");
			if (text == null) {
				int projectId = Integer.valueOf(request.getParameter("id"));
				Project project = projectDao.getProject(projectId);
				request.getSession().setAttribute(PROJECT, project);
				request.getRequestDispatcher(PROJECT_PAGE).forward(request, response);
			} else {
				Project project = (Project) request.getSession().getAttribute(PROJECT);
				projectDao.setQuestion(text, project.getId());
				project = projectDao.getProject(project.getId());
				request.getSession().setAttribute(PROJECT, project);
				request.getRequestDispatcher(PROJECT_PAGE).forward(request, response);
			}
		} catch (SqlConnectionException e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}