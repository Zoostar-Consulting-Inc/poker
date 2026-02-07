package com.zoostarinc.poker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractCommonTest {

	@Autowired
	protected MockMvc endpoint;

	protected ObjectMapper om = objectMapper();

	protected ObjectMapper objectMapper() {
		var value = new ObjectMapper();
		value.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return value;
	}

	protected MockHttpServletResponse getBody(String url) throws Exception {
		return endpoint.perform(get(url).contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
	}

	protected <T> MockHttpServletResponse postJsonRequest(String url, T request) throws Exception {
		return endpoint
				.perform(post(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(om.writeValueAsString(request)))
				.andReturn().getResponse();
	}

}
