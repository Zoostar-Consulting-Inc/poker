package com.zoostarinc.poker.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import com.zoostarinc.poker.AbstractCommonTest;
import com.zoostarinc.poker.dao.entity.PlayerEntity;
import com.zoostarinc.poker.dao.repository.PlayerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SwaggerControllerTest extends AbstractCommonTest {

	@MockitoSpyBean
	PlayerRepository playerRepository;

	@Test
	void testGreetingAsNewUser() throws Exception {
		// given
		String url = "/";

		// when
		var response = getResponse(url);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
	}

	@Test
	void testGreetingAsExistingUser() throws Exception {
		// given
		String url = "/";

		var user = oidcUser();
		var entity = new PlayerEntity();
		entity.setEmail(user.getEmail());

		// when
		when(playerRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(entity));
		var response = getResponse(url);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
	}

}
