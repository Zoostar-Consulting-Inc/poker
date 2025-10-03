package com.zoostarinc.poker.evaluator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

class StraightEvaluatorTest {

	@Test
	void testEvaluate() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.SPADE));
		cards.add(new PokerCard(Face.NINE, Suit.DIAMOND));
		cards.add(new PokerCard(Face.SEVEN, Suit.SPADE));
		cards.add(new PokerCard(Face.SIX, Suit.DIAMOND));
		cards.add(new PokerCard(Face.FIVE, Suit.CLUB));
		cards.add(new PokerCard(Face.FOUR, Suit.HEART));
		cards.add(new PokerCard(Face.THREE, Suit.SPADE));

		var hand = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand.getType()).isEqualTo(PokerHandType.STRAIGHT);

		cards = new TreeSet<>();
		cards.add(new PokerCard(Face.KING, Suit.DIAMOND));
		cards.add(new PokerCard(Face.NINE, Suit.CLUB));
		cards.add(new PokerCard(Face.SIX, Suit.HEART));
		cards.add(new PokerCard(Face.FIVE, Suit.DIAMOND));
		cards.add(new PokerCard(Face.FOUR, Suit.SPADE));
		cards.add(new PokerCard(Face.THREE, Suit.HEART));
		cards.add(new PokerCard(Face.TWO, Suit.SPADE));
		PokerHand lesser = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(lesser).isLessThan(hand);
	}

}
