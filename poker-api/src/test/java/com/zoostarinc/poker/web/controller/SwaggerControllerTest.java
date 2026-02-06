package com.zoostarinc.poker.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.zoostarinc.poker.AbstractCommonTest;

@SpringBootTest
@AutoConfigureMockMvc
class SwaggerControllerTest extends AbstractCommonTest {

	@Test
	void testGreeting() throws Exception {
		// given
		String url = "/";

		// when
		var response = getBody(url);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
	}

    @Test
    void testGreetingAsGuest() throws Exception {
            // given
            String url = "/";

            // when
            var response = endpoint.perform(get(url).contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

            // then
            assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
    }

}
