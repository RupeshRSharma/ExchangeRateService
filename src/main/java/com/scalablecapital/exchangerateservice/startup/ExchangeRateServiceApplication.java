package com.scalablecapital.exchangerateservice.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Start up application class
 */
@SpringBootApplication(scanBasePackages = "com.scalablecapital.exchangerateservice")
public class ExchangeRateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateServiceApplication.class, args);
	}

}
