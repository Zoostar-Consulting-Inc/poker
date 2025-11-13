package com.zoostarinc.poker.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoostarinc.card.Card;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.api.request.PokerHandComparisonRequest;
import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.api.response.PokerHandComparisonResponse;
import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.hand.PokerHandType;

import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.web.response.CommonErrorResponse;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PokerApiTest {

	protected ObjectMapper om = objectMapper();

	@Autowired
	protected MockMvc endpoint;

	public static ObjectMapper objectMapper() {
		var om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om;
	}

	@Test
	void testEvaluateHighCard() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.ACE, Suit.CLUB));
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

	@Test
	void testEvaluateOnePair() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.ACE, Suit.CLUB));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.ONE_PAIR);
	}

	@Test
	void testEvaluateTwoPair() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.ACE, Suit.CLUB));
		cards.add(new Card(Face.SIX, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.TWO, Suit.SPADE));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.TWO_PAIR);
	}

	@Test
	void testEvaluateThreeOfAKind() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.ACE, Suit.CLUB));
		cards.add(new Card(Face.SIX, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.FOUR, Suit.HEART));
		cards.add(new Card(Face.EIGHT, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.THREE_OF_A_KIND);
	}

	@Test
	void testEvaluateStraight() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.SEVEN, Suit.CLUB));
		cards.add(new Card(Face.FIVE, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.FOUR, Suit.HEART));
		cards.add(new Card(Face.EIGHT, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.STRAIGHT);
	}

	@Test
	void testEvaluateFlush() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.SEVEN, Suit.HEART));
		cards.add(new Card(Face.FIVE, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.HEART));
		cards.add(new Card(Face.FOUR, Suit.HEART));
		cards.add(new Card(Face.EIGHT, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.FLUSH);
	}

	@Test
	void testEvaluateFullHouse() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.EIGHT, Suit.CLUB));
		cards.add(new Card(Face.FIVE, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.EIGHT, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.FOUR, Suit.HEART));
		cards.add(new Card(Face.EIGHT, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.FULL_HOUSE);
	}

	@Test
	void testEvaluateFourOfAKind() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.SIX, Suit.CLUB));
		cards.add(new Card(Face.SIX, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.FOUR, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.FOUR_OF_A_KIND);
	}

	@Test
	void testEvaluateStraightFlush() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.SEVEN, Suit.HEART));
		cards.add(new Card(Face.FIVE, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.FOUR, Suit.HEART));
		cards.add(new Card(Face.EIGHT, Suit.HEART));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.STRAIGHT_FLUSH);
	}

	@Test
	void testEvaluateRoyalFlush() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.SEVEN, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.JACK, Suit.HEART));
		cards.add(new Card(Face.QUEEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.KING, Suit.HEART));
		cards.add(new Card(Face.ACE, Suit.HEART));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.ROYAL_FLUSH);
	}

	@Test
	void testCompareGreaterThan() throws Exception {
		// given
		String url = "/compare";
		var cards1 = new ArrayList<Card>();
		cards1.add(new Card(Face.SEVEN, Suit.HEART));

		var cards2 = new ArrayList<Card>();
		cards2.add(new Card(Face.THREE, Suit.SPADE));

		var request = new PokerHandComparisonRequest(cards1, cards2);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandComparisonResponse.class);
		var hands = value.getHands();
		assertThat(hands).hasSize(1);
		var it = hands.iterator();
		var cards = it.next().getCards();
		var card = cards.iterator().next();
		assertThat(card).isEqualTo(cards1.get(0));
	}

	@Test
	void testCompareLessThan() throws Exception {
		// given
		String url = "/compare";
		var cards1 = new ArrayList<Card>();
		cards1.add(new Card(Face.FOUR, Suit.HEART));

		var cards2 = new ArrayList<Card>();
		cards2.add(new Card(Face.QUEEN, Suit.SPADE));

		var request = new PokerHandComparisonRequest(cards1, cards2);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandComparisonResponse.class);
		var hands = value.getHands();
		assertThat(hands).hasSize(1);
		var it = hands.iterator();
		var cards = it.next().getCards();
		var card = cards.iterator().next();
		assertThat(card).isEqualTo(cards2.get(0));
	}

	@Test
	void testCompareEqual() throws Exception {
		// given
		String url = "/compare";
		var cards1 = new ArrayList<Card>();
		cards1.add(new Card(Face.SEVEN, Suit.HEART));

		var cards2 = new ArrayList<Card>();
		cards2.add(new Card(Face.SEVEN, Suit.SPADE));

		var request = new PokerHandComparisonRequest(cards1, cards2);

		// when
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandComparisonResponse.class);
		var hands = value.getHands();
		assertThat(hands).hasSize(2);
	}

	@Test
	void testShuffle() throws Exception {
		// given
		String url = "/shuffle";

		// when
		var response = endpoint.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), new TypeReference<List<Card>>() {
		});
		assertThat(value).hasSize(52);
	}

	@Test
	void testInvalidNumberOfCards() throws Exception {
		// given
		String url = "/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.SEVEN, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.JACK, Suit.HEART));
		cards.add(new Card(Face.QUEEN, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.SPADE));
		cards.add(new Card(Face.KING, Suit.HEART));
		cards.add(new Card(Face.ACE, Suit.HEART));
		cards.add(new Card(Face.ACE, Suit.SPADE));

		var request = new PokerHandEvaluationRequest(cards);
		var response = endpoint.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		var value = om.readValue(response.getContentAsString(), CommonErrorResponse.class);
		assertThat(value.getMessage()).isEqualTo("Expected minimum of 1 and maximum of 7 cards only!");
	}

}
