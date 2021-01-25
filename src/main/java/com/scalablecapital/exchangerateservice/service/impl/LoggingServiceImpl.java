package com.scalablecapital.exchangerateservice.service.impl;

import com.scalablecapital.exchangerateservice.service.LoggingService;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Logging service to log request and response.
 */
@Component
@Log4j2
public class LoggingServiceImpl implements LoggingService {

	// Trace ID header
	private static final String TRACE_ID = "X-B3-TraceId";

	/**
	 * Log HTTP request
	 * @param httpServletRequest Http request
	 */
	@Override
	public void logRequest(HttpServletRequest httpServletRequest) {

		// Create string to be logged with Http method, request URI and request headers
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("REQUEST ");
		stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
		stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
		stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

		// Build parameters map and append if present
		Map<String, String> parameters = buildParametersMap(httpServletRequest);
		if (!parameters.isEmpty()) {
			stringBuilder.append("parameters=[").append(parameters).append("] ");
		}

		// Log the request
		log.info(stringBuilder.toString());

	}

	/**
	 * Log response
	 * @param httpServletResponse Http response
	 */
	@Override
	public void logResponse(HttpServletResponse httpServletResponse) {

		// Create string to be logged
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("RESPONSE ");
		stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse))
				.append("] ");

		// Log the response
		log.info(stringBuilder.toString());
	}

	/**
	 * Build parameters map from request
	 * @param httpServletRequest Http request
	 * @return Request parameters map
	 */
	private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
		Map<String, String> resultMap = new HashMap<>();
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = httpServletRequest.getParameter(key);
			resultMap.put(key, value);
		}

		return resultMap;
	}

	/**
	 * Build headers map from request
	 * @param request Http request
	 * @return Headers map
	 */
	private Map<String, String> buildHeadersMap(HttpServletRequest request) {

		Map<String, String> map = new HashMap<>();

		Enumeration headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * Build headers map from response
	 * @param response Http response
	 * @return Headers map
	 */
	private Map<String, String> buildHeadersMap(HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();

		Collection<String> headerNames = response.getHeaderNames();
		for (String header : headerNames) {
			map.put(header, response.getHeader(header));
		}

		return map;
	}
}
