package com.scalablecapital.exchangerateservice.startup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalablecapital.exchangerateservice.model.Currency;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Currency details load from properties file.
 */
@Component
@Log4j2
public class CurrencyLoader {

	@Autowired // Resource loader for loading
	private ResourceLoader resourceLoader;

	@Getter
	private Map<String, Currency> currencyMap = new HashMap();

	/**
	 * Get currency details for the code
	 * @param currencyCode Currency code
	 * @return Currency
	 */
	public Optional<Currency> getCurrency(String currencyCode) {
		return Optional.ofNullable(this.currencyMap.get(currencyCode));
	}

	/**
	 * Load currency details
	 * @throws IOException
	 */
	@PostConstruct
	public void init() throws IOException {
		this.loadCurrencyDetails();
	}

	/**
	 * Load currency details map from config
	 * @throws IOException
	 */
	private void loadCurrencyDetails() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		Resource localResource = this.resourceLoader.getResource("classpath:currencies.json");

		if (localResource.exists()) {
			try(InputStream inputStream = localResource.getInputStream();){
				this.currencyMap.putAll((Map) mapper.readValue(inputStream, new TypeReference<HashMap<String, Currency>>() {
				}));
			}
		}

		log.info("init method, currency details map loaded");
		log.info(this.currencyMap);
	}
}


