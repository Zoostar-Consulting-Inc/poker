package com.zoostarinc.poker.evaluator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHandType;

class TwoPairEvaluatorTest {

	@Test
	void testEvaluate() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.SPADE));
		cards.add(new PokerCard(Face.TEN, Suit.DIAMOND));
		cards.add(new PokerCard(Face.THREE, Suit.SPADE));
		cards.add(new PokerCard(Face.THREE, Suit.DIAMOND));
		cards.add(new PokerCard(Face.KING, Suit.DIAMOND));
		var hand = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand.getType()).isEqualTo(PokerHandType.TWO_PAIR);

		cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.CLUB));
		cards.add(new PokerCard(Face.TEN, Suit.HEART));
		cards.add(new PokerCard(Face.EIGHT, Suit.SPADE));
		cards.add(new PokerCard(Face.EIGHT, Suit.DIAMOND));
		cards.add(new PokerCard(Face.QUEEN, Suit.DIAMOND));
		var higher = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand).isLessThan(higher);
	}

}
