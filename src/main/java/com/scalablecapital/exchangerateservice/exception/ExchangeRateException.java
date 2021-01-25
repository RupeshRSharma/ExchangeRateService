package com.scalablecapital.exchangerateservice.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Exception for Exchange rate services
 */
@Getter
public class ExchangeRateException extends Exception {

	// Error code
	private final String errorCode;

	// Errors
	private List<Error> errors = new ArrayList();

	public ExchangeRateException(String errorCode) {
		this.errorCode = errorCode;
	}

	public ExchangeRateException(String errorCode, List<Error> errors) {
		this.errorCode = errorCode;
		this.errors = errors;
	}

	public ExchangeRateException(String errorCode, String args) {
		super(args);
		this.errorCode = errorCode;
	}

	public ExchangeRateException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ExchangeRateException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

}
