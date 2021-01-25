package com.scalablecapital.exchangerateservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Return Status of the API.
 */
@Getter
@AllArgsConstructor
@ToString
public enum StatusEnum {
	SUCCESS("SUCCESS"),FAILURE("FAILURE"),PARTIAL_SUCCESS("PARTIAL_SUCCESS");

	// Status value
	private String value;

}
