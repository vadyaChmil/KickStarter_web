package com.rhcloud.vadyazakusylo.dao;

import java.util.List;

import com.rhcloud.vadyazakusylo.entity.Quote;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public interface QuotesDao {

	List<Quote> getQuotesList() throws SqlConnectionException;

}
