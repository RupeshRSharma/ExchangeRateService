package com.scalablecapital.exchangerateservice.service;

import com.scalablecapital.exchangerateservice.exception.ExchangeRateException;
import com.scalablecapital.exchangerateservice.model.Currency;
import com.scalablecapital.exchangerateservice.response.ConversionRate;
import com.scalablecapital.exchangerateservice.response.ExchangeRateResponse;
import com.scalablecapital.exchangerateservice.response.ReferenceRate;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for Exchange rate.
 */
public interface ExchangeRateService {

	/**
	 * Get the reference rate with from/to currency
	 * @param from From currency
	 * @param to To currency
	 * @return Reference rate response
	 */
	ExchangeRateResponse<ReferenceRate> getReferenceRate(String from, String to) throws ExchangeRateException;

	/**
	 * Get the conversion rate with from/to currency
	 * @param from From currency
	 * @param to To currency
	 * @return Conversion rate response
	 */
	ExchangeRateResponse<ConversionRate> getConversionRate(String from, String to, BigDecimal amount) throws ExchangeRateException;

	/**
	 * Get list of currencies with times requested
	 * @return List of currencies
	 */
	ExchangeRateResponse<List<Currency>> getCurrencies();

}
