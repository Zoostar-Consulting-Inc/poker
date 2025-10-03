package com.zoostarinc.poker.evaluator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandHighCard;
import com.zoostarinc.poker.hand.PokerHandType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class HighCardEvaluatorTest {

	@Test
	void testEvaluate() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.SPADE));
		cards.add(new PokerCard(Face.NINE, Suit.SPADE));
		cards.add(new PokerCard(Face.EIGHT, Suit.SPADE));
		var hand = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand.getType()).isEqualTo(PokerHandType.HIGH_CARD);

		cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.HEART));
		cards.add(new PokerCard(Face.NINE, Suit.HEART));
		cards.add(new PokerCard(Face.SEVEN, Suit.HEART));
		PokerHand lesser = new PokerHandHighCard(cards);
		assertThat(lesser).isLessThan(hand);
	}

	@Test
	void testNullCollection() {
		assertThatThrownBy(() -> DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(DefaultPokerHandEvaluatorChain.ILLEGAL_ARG_EXCEPTION_MSG);
	}

	@Test
	void testEmptyCollection() {
		assertThatThrownBy(() -> DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(new TreeSet<>()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(DefaultPokerHandEvaluatorChain.ILLEGAL_ARG_EXCEPTION_MSG);
	}

	@Test
	void testMoreThanMaxCollection() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.ACE, Suit.CLUB));
		cards.add(new PokerCard(Face.KING, Suit.CLUB));
		cards.add(new PokerCard(Face.QUEEN, Suit.CLUB));
		cards.add(new PokerCard(Face.JACK, Suit.CLUB));
		cards.add(new PokerCard(Face.TEN, Suit.CLUB));
		cards.add(new PokerCard(Face.NINE, Suit.CLUB));
		cards.add(new PokerCard(Face.EIGHT, Suit.CLUB));
		cards.add(new PokerCard(Face.SEVEN, Suit.CLUB));

		assertThatThrownBy(() -> DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(DefaultPokerHandEvaluatorChain.ILLEGAL_ARG_EXCEPTION_MSG);
	}

}
