package com.rhcloud.vadyazakusylo.dao;

import java.util.List;

import com.rhcloud.vadyazakusylo.entity.Category;
import com.rhcloud.vadyazakusylo.exception.SqlConnectionException;

public interface CategoriesDao {

	List<Category> getCategoriesList() throws SqlConnectionException;

}
