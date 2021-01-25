package com.scalablecapital.exchangerateservice.interceptor;

import com.scalablecapital.exchangerateservice.service.LoggingService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Log interceptor to log request and response.
 */
@Component
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

	/**
	 * The Logging service.
	 */
	private final LoggingService loggingService;

	/**
	 * Log request
	 * @param request Http request
	 * @param response Http response
	 * @param handler Object handler
	 * @return true
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		loggingService.logRequest(request);

		return true;
	}

	/**
	 * Log response
	 * @param request Http request
	 * @param response Http response
	 * @param handler Object handler
	 * @param modelAndView Model and view of response
	 * @throws Exception Any exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		loggingService.logResponse(response);
	}

}

