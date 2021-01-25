package com.scalablecapital.exchangerateservice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * Logging service to log request and response.
 */
@Component
public interface LoggingService {

	/**
	 * Log HTTP request
	 * @param httpServletRequest Http request
	 */
	void logRequest(HttpServletRequest httpServletRequest);

	/**
	 * Log response
	 * @param httpServletResponse Http response
	 */
	void logResponse(HttpServletResponse httpServletResponse);

}
