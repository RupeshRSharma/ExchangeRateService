package com.scalablecapital.exchangerateservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

@Data
public class Currency implements Serializable {

	// Currency code
	private String code;

	// Currency name
	private String name;

	//Currency value
	private BigDecimal value;

	// No of times currency was requested
	private final AtomicInteger timesRequested = new AtomicInteger(0);

	/**
	 * Increment no of times currency was requested
	 * @return Times currency was requested
	 */
	public int incrementTimesRequested() {
		return timesRequested.incrementAndGet();
	}

}
