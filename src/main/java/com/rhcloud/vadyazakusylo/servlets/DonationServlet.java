package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public class DonationServlet extends HttpKickStarter {

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

			int projectId = Integer.valueOf(request.getParameter("id"));
			if (request.getParameter("money") != null) {
				int money = Integer.valueOf(request.getParameter("money"));
				request.setAttribute("money", money);
			}
			request.setAttribute("projectId", projectId);
			Map<Integer, String> donations = projectDao.getDonatios(projectId);
			request.setAttribute(DONATIONS, donations);
			request.getRequestDispatcher(DONATION_PAGE).forward(request, response);

		} catch (SqlConnectionException e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}