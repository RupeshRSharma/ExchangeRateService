package com.scalablecapital.exchangerateservice.util;

import com.scalablecapital.exchangerateservice.constant.StatusEnum;
import com.scalablecapital.exchangerateservice.response.ExchangeRateResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class that will have static utility methods
 */

public class CommonUtil {

	/**
	 * Generate success response response.
	 * @param <T>
	 *          the type parameter
	 * @param data
	 *          the data
	 * @return the response
	 */
	public static <T> ExchangeRateResponse<T> generateSuccessResponse(T data) {
		ExchangeRateResponse<T> response = generateSuccessResponse();
		response.setData(data);
		return response;

	}

	/**
	 * Generate success response response.
	 * @param <T>
	 *          the type parameter
	 * @return the response
	 */
	public static <T> ExchangeRateResponse<T> generateSuccessResponse() {
		ExchangeRateResponse<T> response = new ExchangeRateResponse<>();
		response.setStatus(StatusEnum.SUCCESS);
		return response;

	}

	/**
	 * Calculate reference rate
	 * @param from from currency value
	 * @param to to currency value
	 * @return Calculated reference rate
	 */
	public static BigDecimal calculateReferenceRate(BigDecimal from, BigDecimal to){
		return from.divide(to, 4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Calculate conversion rate
	 * @param from from currency value
	 * @param to to currency value
	 * @return Calculated conversion rate
	 */
	public static BigDecimal calculateConversionRate(BigDecimal amount, BigDecimal from, BigDecimal to){
		return amount.multiply(to).divide(from, 4, RoundingMode.HALF_UP);
	}

}

