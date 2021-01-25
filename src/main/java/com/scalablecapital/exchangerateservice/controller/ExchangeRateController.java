package com.scalablecapital.exchangerateservice.controller;

import com.scalablecapital.exchangerateservice.constant.EndpointConstants;
import com.scalablecapital.exchangerateservice.exception.ExchangeRateException;
import com.scalablecapital.exchangerateservice.model.Currency;
import com.scalablecapital.exchangerateservice.response.ConversionRate;
import com.scalablecapital.exchangerateservice.response.ExchangeRateResponse;
import com.scalablecapital.exchangerateservice.response.ReferenceRate;
import com.scalablecapital.exchangerateservice.service.ExchangeRateService;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Exchange rate related endpoints.
 * <p>
 * 1. /exchange-rates/reference-rate:GET - Calculate Reference rate of 2 currencies
 * </p>
 * <p>
 * 2./exchange-rates/conversion-rate:GET - Calculate Conversion rate from one currency to another for a given amount
 * </p>
 * <p>
 * 3. /exchange-rates/currencies:GET - Get all the currencies with no of times they were requested
 * </p>
 */
@RestController
@RequestMapping(EndpointConstants.BASE_V1_API + EndpointConstants.EXCHANGE_RATES)
@Log4j2
@AllArgsConstructor
public class ExchangeRateController {

	// Exchange rate service
	private final ExchangeRateService exchangeRateService;

	/**
	 * Calculate reference rate of 2 currencies
	 * @param fromCurrency From currency
	 * @param toCurrency To currency
	 * @return Reference rate
	 * @throws ExchangeRateException Any exception
	 */
	@GetMapping(EndpointConstants.REFERENCE_RATE)
	public ResponseEntity<ExchangeRateResponse<ReferenceRate>> getReferenceRate(
			@NotBlank @RequestParam(name = "fromCurrency") String fromCurrency,
			@NotBlank @RequestParam(name = "toCurrency") String toCurrency) throws ExchangeRateException {

		log.info("Reference rate from {} to {}",fromCurrency, toCurrency);

		return new ResponseEntity<>(exchangeRateService.getReferenceRate(fromCurrency, toCurrency),	HttpStatus.OK);
	}

	/**
	 * Calculate reference rate of 2 currencies
	 * @param fromCurrency From currency
	 * @param toCurrency To currency
	 * @return Reference rate
	 * @throws ExchangeRateException Any exception
	 */
	@GetMapping(EndpointConstants.CONVERSION_RATE)
	public ResponseEntity<ExchangeRateResponse<ConversionRate>> getConversionRate(
			@NotBlank @RequestParam(name = "fromCurrency") String fromCurrency,
			@NotBlank @RequestParam(name = "toCurrency") String toCurrency,
			@NotBlank @RequestParam(name = "amount") BigDecimal amount) throws ExchangeRateException {

		log.info("Conversion rate from {} to {} for amount {}",fromCurrency, toCurrency, amount);

		return new ResponseEntity<>(exchangeRateService.getConversionRate(fromCurrency, toCurrency, amount),	HttpStatus.OK);
	}

	/**
	 * Get list of currencies with no of times they were requested
	 * @return List of currencies
	 * @throws ExchangeRateException Any exception
	 */
	@GetMapping(EndpointConstants.CURRENCIES)
	public ResponseEntity<ExchangeRateResponse<List<Currency>>> getCurrencies() {

		log.info("Get list of currencies");

		return new ResponseEntity<>(exchangeRateService.getCurrencies(), HttpStatus.OK);
	}

}
