package com.scalablecapital.exchangerateservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalablecapital.exchangerateservice.constant.StatusEnum;
import com.scalablecapital.exchangerateservice.model.Currency;
import com.scalablecapital.exchangerateservice.response.ConversionRate;
import com.scalablecapital.exchangerateservice.response.ExchangeRateResponse;
import com.scalablecapital.exchangerateservice.response.ReferenceRate;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Exchange controller test
 */
public class ExchangeRateIntegrationTest extends AbstractTest {

	// Reference rate URI
	private static final String REFERENCE_RATE_URI = "/1.0/exchange-rates/reference-rate?fromCurrency=USD&toCurrency=EUR";

	// Conversion rate URI
	private static final String CONVERSION_RATE_URI = "/1.0/exchange-rates/conversion-rate?fromCurrency=USD&toCurrency=EUR&amount=10.0";

	// Get Currencies URI
	private static final String CURRENCIES_URI = "/1.0/exchange-rates/currencies";

	// Object mapper
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Initialize setup before tests.
	 */
	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
	}

	/**
	 * Get reference rate test
	 * @throws Exception MVC exception
	 */
	@Test
	public void getReferenceRate() throws Exception {

		// Execute the request
		ExchangeRateResponse response = getExchangeRateResponse(REFERENCE_RATE_URI);

		// Map Reference rate from response data
		ReferenceRate referenceRate = mapper.convertValue(response.getData(), new TypeReference<ReferenceRate>() {
		});

		// assertions on reference rate
		Assertions.assertEquals("USD/EUR", referenceRate.getCurrencyPair());
		Assertions.assertEquals(BigDecimal.valueOf(1.21), referenceRate.getReferenceRate());
		Assertions.assertEquals("https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/eurofxref-graph-usd.en.html", referenceRate.getLink());

	}

	/**
	 * Get conversion rate test
	 * @throws Exception MVC exception
	 */
	@Test
	public void getConversionRate() throws Exception {

		// Execute the request
		ExchangeRateResponse response = getExchangeRateResponse(CONVERSION_RATE_URI);

		// Map Conversion rate from response data
		ConversionRate conversionRate = mapper.convertValue(response.getData(), new TypeReference<ConversionRate>() {
		});

		// assertions on conversion rate
		Assertions.assertEquals("USD", conversionRate.getFromCurrency());
		Assertions.assertEquals("EUR", conversionRate.getToCurrency());
		Assertions.assertEquals(BigDecimal.valueOf(10.0), conversionRate.getFromAmount());
		Assertions.assertEquals(BigDecimal.valueOf(8.26), conversionRate.getConvertedAmount());

	}

	/**
	 * Get all currencies test
	 * @throws Exception MVC exception
	 */
	@Test
	public void getCurrencies() throws Exception {

		ExchangeRateResponse response = getExchangeRateResponse(CURRENCIES_URI);

		// Map currency list from response data
		List<Currency> currencyList = mapper.convertValue(response.getData(), new TypeReference<List<Currency>>() {
		});

		// assertions on currency list
		Assertions.assertNotNull(currencyList);
		Assertions.assertEquals(4, currencyList.size());

	}

	/**
	 * Get exchange rate response
	 *
	 * @param uri URI
	 * @return ExchangeRateResponse
	 * @throws Exception MVC exception
	 */
	private ExchangeRateResponse getExchangeRateResponse(String uri) throws Exception {

		// Execute the request
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		// getting response body as JSON
		ExchangeRateResponse response = this.mapFromJson(mvcResult.getResponse().getContentAsString(), ExchangeRateResponse.class);

		// assertions on response
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
		Assertions.assertTrue(response.getStatus().equals(StatusEnum.SUCCESS));
		Assertions.assertNotNull(response.getData());

		return response;
	}

}