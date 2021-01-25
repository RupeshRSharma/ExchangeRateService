package com.scalablecapital.exchangerateservice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalablecapital.exchangerateservice.startup.ExchangeRateServiceApplication;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * The type Abstract test.
 */
@SpringBootTest(classes = ExchangeRateServiceApplication.class)
@WebAppConfiguration
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {
	/**
	 * The Mvc.
	 */
	protected MockMvc mvc;

	/**
	 * The Web application context.
	 */
	@Autowired
	WebApplicationContext webApplicationContext;

	/**
	 * Sets up.
	 */
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Map from json t.
	 * @param <T>
	 *          the type parameter
	 * @param json
	 *          the json
	 * @param clazz
	 *          the clazz
	 * @return the t
	 * @throws JsonMappingException
	 *           the json mapping exception
	 * @throws IOException
	 *           the io exception
	 */
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
