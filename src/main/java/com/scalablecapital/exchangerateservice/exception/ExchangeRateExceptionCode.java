package com.scalablecapital.exchangerateservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception codes for Exchange rate service
 */
@AllArgsConstructor
@Getter
public enum ExchangeRateExceptionCode {

	// Invalid currency code present in the request
	INVALID_CURRENCY_CODE("ERS_101");

	// Exception code
	private final String code;

}
