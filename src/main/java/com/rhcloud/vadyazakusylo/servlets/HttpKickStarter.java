package com.rhcloud.vadyazakusylo.servlets;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.rhcloud.vadyazakusylo.dao.CategoriesDao;
import com.rhcloud.vadyazakusylo.dao.ProjectDao;
import com.rhcloud.vadyazakusylo.dao.ProjectsDao;
import com.rhcloud.vadyazakusylo.dao.QuotesDao;
import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

@Controller
public abstract class HttpKickStarter extends HttpServlet {

	/**
	 * Vadya Zakusylo
	 */
	private static final long serialVersionUID = 1L;

	static final String QUOTE = "quote";
	static final String CATEGORIES = "categories";
	static final String PROJECTS = "projects";
	static final String PROJECT = "project";
	static final String DONATIONS = "donations";
	static final String ERROR_MESSAGE = "errorMessage";

	static final String CATEGORIES_PAGE = "categories.jsp";
	static final String CATEGORY_PAGE = "category.jsp";
	static final String PROJECT_PAGE = "project.jsp";
	static final String DONATION_PAGE = "donation.jsp";
	static final String ERROR_PAGE = "error.jsp";

	@Autowired
	QuotesDao quotesDao;

	@Autowired
	CategoriesDao categoriesDao;

	@Autowired
	ProjectsDao projectsDao;

	@Autowired
	ProjectDao projectDao;

	@Override
	public void init(ServletConfig config) throws javax.servlet.ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	Quote getQuote() throws SqlConnectionException {
		try {
			List<Quote> quotesList = quotesDao.getQuotesList();
			Quote quote = quotesList.get((int) Math.round((Math.random() * (quotesList.size() - 1))));
			return quote;
		} catch (SqlConnectionException e) {
			throw new SqlConnectionException("Problem with connecting to DataBase");
		}
	}
}
