package com.scalablecapital.exchangerateservice.service.impl;

import com.scalablecapital.exchangerateservice.exception.ExchangeRateException;
import com.scalablecapital.exchangerateservice.exception.ExchangeRateExceptionCode;
import com.scalablecapital.exchangerateservice.model.Currency;
import com.scalablecapital.exchangerateservice.response.ConversionRate;
import com.scalablecapital.exchangerateservice.response.ExchangeRateResponse;
import com.scalablecapital.exchangerateservice.response.ReferenceRate;
import com.scalablecapital.exchangerateservice.service.ExchangeRateService;
import com.scalablecapital.exchangerateservice.startup.CurrencyLoader;
import com.scalablecapital.exchangerateservice.util.CommonUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class for Exchange rate.
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	// Currency loader to load currencies
	private final CurrencyLoader currencyLoader;

	// ECB Reference rate link
	@Value("${ecb.reference.rate.link}")
	private String ecbReferenceRateLink;

	//Inject currency loader
	public ExchangeRateServiceImpl(CurrencyLoader currencyLoader) {
		this.currencyLoader = currencyLoader;
	}

	/**
	 * Get the reference rate with from/to currency
	 * @param from From currency
	 * @param to To currency
	 * @return Reference rate response
	 */
	@Override
	public ExchangeRateResponse<ReferenceRate> getReferenceRate(String from, String to) throws ExchangeRateException {

		// Get from currency from the currency map and increment the requested counter
		Currency fromCurrency = getCurrency(from);
		fromCurrency.incrementTimesRequested();

		// Get to currency from the currency map and increment the requested counter
		Currency toCurrency = getCurrency(to);
		toCurrency.incrementTimesRequested();

		// generate the response and return
		return CommonUtil.generateSuccessResponse(ReferenceRate.builder().currencyPair(from + "/" + to)
				.referenceRate(CommonUtil.calculateReferenceRate(fromCurrency.getValue(), toCurrency.getValue()))
				.link(String.format(ecbReferenceRateLink, from.toLowerCase()))
				.build());

	}

	/**
	 * Get the conversion rate with from/to currency
	 * @param from From currency
	 * @param to To currency
	 * @return Conversion rate response
	 */
	@Override
	public ExchangeRateResponse<ConversionRate> getConversionRate(String from, String to, BigDecimal amount) throws ExchangeRateException {

		// Get from currency from the currency map and increment the requested counter
		Currency fromCurrency = getCurrency(from);
		fromCurrency.incrementTimesRequested();

		// Get to currency from the currency map and increment the requested counter
		Currency toCurrency = getCurrency(to);
		toCurrency.incrementTimesRequested();

		// generate the response and return
		return CommonUtil.generateSuccessResponse(ConversionRate.builder().fromCurrency(from).toCurrency(to).fromAmount(amount)
				.convertedAmount(CommonUtil.calculateConversionRate(amount, fromCurrency.getValue(), toCurrency.getValue()))
				.build());

	}

	/**
	 * Get list of currencies with times requested
	 * @return List of currencies
	 */
	@Override
	public ExchangeRateResponse<List<Currency>> getCurrencies() {

		return CommonUtil.generateSuccessResponse(currencyLoader.getCurrencyMap().values().stream().collect(Collectors.toList()));
	}

	/**
	 * Get currency for the code
	 * @param currencyCode Currency code
	 * @return Currency
	 * @throws ExchangeRateException When currency code is not found in the currency map
	 */
	private Currency getCurrency(String currencyCode) throws ExchangeRateException {
		return currencyLoader.getCurrency(currencyCode).orElseThrow(() -> new ExchangeRateException(ExchangeRateExceptionCode.INVALID_CURRENCY_CODE.getCode()));
	}

}
