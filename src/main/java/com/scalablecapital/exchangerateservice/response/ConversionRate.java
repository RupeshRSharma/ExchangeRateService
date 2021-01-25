package com.scalablecapital.exchangerateservice.response;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Conversion rate response
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRate implements Serializable {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -8369305027345104918L;

	// Currency from
	private String fromCurrency;

	// Currency to
	private String toCurrency;

	// Currency from amount
	private BigDecimal fromAmount;

	// Currency to amount
	private BigDecimal convertedAmount;

}

