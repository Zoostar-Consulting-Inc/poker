package com.zoostarinc.poker.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHandType;

@SpringBootTest
@AutoConfigureMockMvc
class PokerHandEvaluatorApiTest {

	protected ObjectMapper om = objectMapper();

	@Autowired
	protected MockMvc endpoint;

	public static ObjectMapper objectMapper() {
		var om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om;
	}

	@Test
	void testEvaluate() throws Exception {
		// given
		String url = "/evaluate";
		Collection<PokerCard> cards = new ArrayList<>();
		cards.add(new PokerCard(Face.ACE, Suit.CLUB));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.HIGH_CARD);
	}

}
