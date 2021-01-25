package com.scalablecapital.exchangerateservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic Error Object.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Cloneable {

	/**
	 * The Severity enum.
	 */
	public enum SeverityEnum {
		WARNING, INFO,FATAL;
	}

	// Error code
	private String code = null;

	// Error severity
	private SeverityEnum severity = null;

	// Error message
	private String message = null;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
