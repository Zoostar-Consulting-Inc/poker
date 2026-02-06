package com.zoostarinc.poker.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zoostarinc.card.Card;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.AbstractCommonTest;
import com.zoostarinc.poker.api.request.PokerHandComparisonRequest;
import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.api.response.PokerHandComparisonResponse;
import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.web.response.CommonErrorResponse;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PokerRestControllerTest extends AbstractCommonTest {

	@Test
	void testEvaluateHighCard() throws Exception {
		// given
		String url = "/api/evaluate";
		var card = new Card(Face.ACE, Suit.CLUB);
		var cards = new ArrayList<Card>();
		cards.add(card);
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();

		var expectedHand = new PokerHand(PokerHandType.HIGH_CARD, cards);
		var actualHand = new PokerHand(value.getType(), value.getCards());

		assertThat(expectedHand).isEqualTo(actualHand).hasSameHashCodeAs(actualHand);
		var it = value.getCards().iterator();
		if (it.hasNext()) {
			var element = it.next();
			assertThat(element).isEqualTo(card).hasSameHashCodeAs(card);
			assertThat(element).isNotEqualTo(new Card(Face.EIGHT, Suit.CLUB));
		} else {
			fail("Expecting at least 1 element!");
		}
	}

	@Test
	void testEvaluateOnePair() throws Exception {
		// given
		String url = "/api/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.ACE, Suit.CLUB));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.ONE_PAIR);
	}

	@Test
	void testEvaluateTwoPair() throws Exception {
		// given
		String url = "/api/evaluate";
		var cards = new ArrayList<Card>();
		cards.add(new Card(Face.ACE, Suit.CLUB));
		cards.add(new Card(Face.SIX, Suit.HEART));
		cards.add(new Card(Face.SIX, Suit.DIAMOND));
		cards.add(new Card(Face.TWO, Suit.HEART));
		cards.add(new Card(Face.TWO, Suit.SPADE));
		cards.add(new Card(Face.TEN, Suit.HEART));
		cards.add(new Card(Face.TEN, Suit.DIAMOND));
		var request = new PokerHandEvaluationRequest(cards);

		// when
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.TWO_PAIR);
	}

	@Test
	void testEvaluateThreeOfAKind() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.THREE_OF_A_KIND);
	}

	@Test
	void testEvaluateStraight() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.STRAIGHT);
	}

	@Test
	void testEvaluateFlush() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.FLUSH);
	}

	@Test
	void testEvaluateFullHouse() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.FULL_HOUSE);
	}

	@Test
	void testEvaluateFourOfAKind() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.FOUR_OF_A_KIND);
	}

	@Test
	void testEvaluateStraightFlush() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.STRAIGHT_FLUSH);
	}

	@Test
	void testEvaluateRoyalFlush() throws Exception {
		// given
		String url = "/api/evaluate";
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
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);
		assertThat(value).isNotNull();
		assertThat(value.getType()).isEqualTo(PokerHandType.ROYAL_FLUSH);
	}

	@Test
	void testCompareGreaterThan() throws Exception {
		// given
		String url = "/api/compare";
		var cards1 = new ArrayList<Card>();
		cards1.add(new Card(Face.SEVEN, Suit.HEART));

		var cards2 = new ArrayList<Card>();
		cards2.add(new Card(Face.THREE, Suit.SPADE));

		var request = new PokerHandComparisonRequest(cards1, cards2);

		// when
		var response = postJsonRequest(url, request);

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
		String url = "/api/compare";
		var cards1 = new ArrayList<Card>();
		cards1.add(new Card(Face.FOUR, Suit.HEART));

		var cards2 = new ArrayList<Card>();
		cards2.add(new Card(Face.QUEEN, Suit.SPADE));

		var request = new PokerHandComparisonRequest(cards1, cards2);

		// when
		var response = postJsonRequest(url, request);

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
		String url = "/api/compare";
		var cards1 = new ArrayList<Card>();
		cards1.add(new Card(Face.SEVEN, Suit.HEART));

		var cards2 = new ArrayList<Card>();
		cards2.add(new Card(Face.SEVEN, Suit.SPADE));

		var request = new PokerHandComparisonRequest(cards1, cards2);

		// when
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandComparisonResponse.class);
		var hands = value.getHands();
		assertThat(hands).hasSize(2);
	}

	@Test
	void testShuffle() throws Exception {
		// given
		String url = "/api/shuffle";

		// when
		var response = getBody(url);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), new TypeReference<List<Card>>() {
		});
		assertThat(value).hasSize(52);
	}

	@Test
	void testInvalidNumberOfCards() throws Exception {
		// given
		String url = "/api/evaluate";
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

		// when
		var response = postJsonRequest(url, request);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		var value = om.readValue(response.getContentAsString(), CommonErrorResponse.class);
		assertThat(value.getMessage()).isEqualTo("Expected minimum of 1 and maximum of 7 cards only!");
	}

	@Test
	void testComparePokerHands() throws Exception {
		// given
		String url = "/api/compare";

		var cardsFlush = new ArrayList<Card>();
		cardsFlush.add(new Card(Face.SEVEN, Suit.HEART));
		cardsFlush.add(new Card(Face.FIVE, Suit.HEART));
		cardsFlush.add(new Card(Face.SIX, Suit.DIAMOND));
		cardsFlush.add(new Card(Face.TEN, Suit.HEART));
		cardsFlush.add(new Card(Face.SIX, Suit.HEART));
		cardsFlush.add(new Card(Face.FOUR, Suit.HEART));
		cardsFlush.add(new Card(Face.EIGHT, Suit.DIAMOND));

		var cardsFullHouse = new ArrayList<Card>();
		cardsFullHouse.add(new Card(Face.EIGHT, Suit.CLUB));
		cardsFullHouse.add(new Card(Face.FIVE, Suit.HEART));
		cardsFullHouse.add(new Card(Face.SIX, Suit.DIAMOND));
		cardsFullHouse.add(new Card(Face.EIGHT, Suit.HEART));
		cardsFullHouse.add(new Card(Face.SIX, Suit.SPADE));
		cardsFullHouse.add(new Card(Face.FOUR, Suit.HEART));
		cardsFullHouse.add(new Card(Face.EIGHT, Suit.DIAMOND));
		var response = postJsonRequest("/api/evaluate", new PokerHandEvaluationRequest(cardsFullHouse));
		var fullHouse = om.readValue(response.getContentAsString(), PokerHandEvaluationResponse.class);

		// when
		response = postJsonRequest(url, new PokerHandComparisonRequest(cardsFullHouse, cardsFlush));

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		var value = om.readValue(response.getContentAsString(), PokerHandComparisonResponse.class);
		assertThat(value.getHands().size()).isEqualTo(1);
		for (var hand : value.getHands()) {
			assertThat(hand.getType()).isEqualTo(fullHouse.getType());
		}
	}

}
