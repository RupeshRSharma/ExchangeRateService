package com.scalablecapital.exchangerateservice.advice;

import com.scalablecapital.exchangerateservice.constant.StatusEnum;
import com.scalablecapital.exchangerateservice.exception.ErrorCodes;
import com.scalablecapital.exchangerateservice.exception.ExchangeRateException;
import com.scalablecapital.exchangerateservice.response.ExchangeRateResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler for ExceptionRateController.
 */
@ControllerAdvice
@Log4j2
@AllArgsConstructor
public class ExchangeRateControllerAdvice {

	private final ErrorCodes errorCodes; // Error codes

	/**
	 * Handle ExchangeRateException, return Http status 200 OK with error details.
	 * @param exception
	 *          {@link ExchangeRateException}
	 * @return Response entity with Http status 200 OK and error details
	 */
	@ExceptionHandler(ExchangeRateException.class)
	ResponseEntity<ExchangeRateResponse> handlePaymentProcessingException(ExchangeRateException exception){

		ExchangeRateResponse response = new ExchangeRateResponse();
		response.setStatus(StatusEnum.FAILURE);
		response.addError(errorCodes.getError(exception.getErrorCode()));

		log.error("Exchange Rate Exception Occurred:", exception);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Handle validation exceptions response entity.
	 * @param ex
	 *          the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExchangeRateResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		ExchangeRateResponse<Object> responseObject = new ExchangeRateResponse<>();
		List<String> errors = new ArrayList<>();
		responseObject.setStatus(StatusEnum.FAILURE);
		ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

		log.error("Request validation failed :", ex);
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}


}