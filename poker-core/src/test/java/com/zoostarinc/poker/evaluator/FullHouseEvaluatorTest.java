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

class FullHouseEvaluatorTest {

	@Test
	void testEvaluate() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.SIX, Suit.SPADE));
		cards.add(new PokerCard(Face.SIX, Suit.CLUB));
		cards.add(new PokerCard(Face.SIX, Suit.DIAMOND));
		cards.add(new PokerCard(Face.EIGHT, Suit.DIAMOND));
		cards.add(new PokerCard(Face.EIGHT, Suit.SPADE));
		cards.add(new PokerCard(Face.KING, Suit.HEART));
		cards.add(new PokerCard(Face.ACE, Suit.SPADE));

		var hand = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand.getType()).isEqualTo(PokerHandType.FULL_HOUSE);

		cards = new TreeSet<>();
		cards.add(new PokerCard(Face.SIX, Suit.SPADE));
		cards.add(new PokerCard(Face.SIX, Suit.HEART));
		cards.add(new PokerCard(Face.EIGHT, Suit.CLUB));
		cards.add(new PokerCard(Face.EIGHT, Suit.DIAMOND));
		cards.add(new PokerCard(Face.EIGHT, Suit.SPADE));
		cards.add(new PokerCard(Face.KING, Suit.HEART));
		cards.add(new PokerCard(Face.ACE, Suit.SPADE));
		PokerHand greater = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(greater).isGreaterThan(hand);
	}

}
