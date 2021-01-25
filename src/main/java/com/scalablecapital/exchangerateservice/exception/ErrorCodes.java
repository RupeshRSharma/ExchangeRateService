package com.scalablecapital.exchangerateservice.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Error codes for exception handling.
 */
@Component
@Log4j2
public class ErrorCodes {

	@Autowired // Resource loader for loading
	private ResourceLoader resourceLoader;

	private Map<String, Error> errorMap = new HashMap();

	/**
	 * Get error for the code
	 * @param code Error code
	 * @return Error
	 */
	public Error getError(String code) {
		return (Error)this.errorMap.get(code);
	}

	/**
	 * Load error codes
	 * @throws IOException
	 */
	@PostConstruct
	public void init() throws IOException {
		this.loadErrorCodeMap();
	}

	/**
	 * Load error code map from config
	 * @throws IOException
	 */
	private void loadErrorCodeMap() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		Resource localResource = this.resourceLoader.getResource("classpath:errors.json");

		if (localResource.exists()) {
			try(InputStream inputStream = localResource.getInputStream();){
				this.errorMap.putAll((Map) mapper.readValue(inputStream, new TypeReference<HashMap<String, Error>>() {
				}));
			}
		}

		log.info("init method, error code map loaded");
		log.info(this.errorMap);
	}
}

