package com.scalablecapital.exchangerateservice.response;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Reference rate response
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceRate implements Serializable {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -3857228290627807056L;

	// Currency pair in from/to format
	private String currencyPair;

	// reference rate of the currency
	private BigDecimal referenceRate;

	// Public website link for showing the graph of the currency conversion
	private String link;

}
