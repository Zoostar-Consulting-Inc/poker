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

class RoyalFlushEvaluatorTest {

	@Test
	void testEvaluate() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.ACE, Suit.SPADE));
		cards.add(new PokerCard(Face.KING, Suit.SPADE));
		cards.add(new PokerCard(Face.QUEEN, Suit.SPADE));
		cards.add(new PokerCard(Face.JACK, Suit.SPADE));
		cards.add(new PokerCard(Face.TEN, Suit.SPADE));
		cards.add(new PokerCard(Face.FOUR, Suit.SPADE));
		cards.add(new PokerCard(Face.THREE, Suit.SPADE));

		var hand = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand.getType()).isEqualTo(PokerHandType.ROYAL_FLUSH);

		cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.HEART));
		cards.add(new PokerCard(Face.FOUR, Suit.CLUB));
		cards.add(new PokerCard(Face.JACK, Suit.HEART));
		cards.add(new PokerCard(Face.QUEEN, Suit.HEART));
		cards.add(new PokerCard(Face.THREE, Suit.CLUB));
		cards.add(new PokerCard(Face.KING, Suit.HEART));
		cards.add(new PokerCard(Face.ACE, Suit.HEART));
		PokerHand equal = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(equal).isEqualByComparingTo(hand);
	}

}
