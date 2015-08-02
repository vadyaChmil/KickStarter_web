package com.rhcloud.vadyazakusylo.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.vadyazakusylo.entity.Quote;

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

			int projectId = Integer.valueOf(request.getParameter(ID));
			if (request.getParameter(MONEY) != null) {
				int money = Integer.valueOf(request.getParameter(MONEY));
				request.setAttribute(MONEY, money);
			}
			request.setAttribute(PROJECT_ID, projectId);
			Map<Integer, String> donations = projectDao.getDonations(projectId);
			request.setAttribute(DONATIONS, donations);
			request.getRequestDispatcher(DONATION_PAGE).forward(request, response);

		} catch (Exception e) {
			String errorMessage = e.getMessage();
			request.setAttribute(ERROR_MESSAGE, errorMessage);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}