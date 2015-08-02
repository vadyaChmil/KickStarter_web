package com.rhcloud.vadyazakusylo.exception;

@SuppressWarnings("serial")
public class DaoSQLException extends RuntimeException {

	public DaoSQLException() {
		super();
	}

	public DaoSQLException(String message) {
		super(message);
	}

	public DaoSQLException(String message, Throwable cause) {
		super(message, cause);
	}
}
