package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.entity.Project;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public class DonateServlet extends HttpKickStarter {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Quote quote = getQuote();
			request.setAttribute(QUOTE, quote);

			Project project = (Project) request.getSession().getAttribute(PROJECT);

			if (request.getParameter("money") == "") {
				Map<Integer, String> donations = projectDao.getDonatios(project.getId());
				request.setAttribute("projectId", project.getId());
				request.setAttribute(DONATIONS, donations);
				request.getRequestDispatcher(DONATION_PAGE).forward(request, response);
			} else {
				int money = Integer.valueOf(request.getParameter("money"));
				projectDao.setCurrentMoney(money + project.getCurrentMoney(), project.getId());
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
