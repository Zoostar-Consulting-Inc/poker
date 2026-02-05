package com.zoostarinc.poker.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.zoostarinc.poker.AbstractCommonTest;

@SpringBootTest
@AutoConfigureMockMvc
class SwaggerControllerTest extends AbstractCommonTest {

	@Test
	void testGreeting() throws Exception {
		// given
		String url = "/";

		// when
		var response = endpoint.perform(get(url).with(oidcLogin().oidcUser(oidcUser()))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
	}

}
