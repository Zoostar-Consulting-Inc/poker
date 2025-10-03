package com.zoostarinc.poker.evaluator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHandType;

class ThreeOfAKindEvaluatorTest {

	@Test
	void testEvaluate() {
		SortedSet<PokerCard> cards = new TreeSet<>();
		cards.add(new PokerCard(Face.TEN, Suit.SPADE));
		cards.add(new PokerCard(Face.TEN, Suit.DIAMOND));
		cards.add(new PokerCard(Face.TEN, Suit.HEART));
		cards.add(new PokerCard(Face.QUEEN, Suit.DIAMOND));
		cards.add(new PokerCard(Face.FIVE, Suit.DIAMOND));
		var hand = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(hand.getType()).isEqualTo(PokerHandType.THREE_OF_A_KIND);

		cards = new TreeSet<>();
		cards.add(new PokerCard(Face.FIVE, Suit.SPADE));
		cards.add(new PokerCard(Face.FIVE, Suit.DIAMOND));
		cards.add(new PokerCard(Face.FIVE, Suit.HEART));
		cards.add(new PokerCard(Face.KING, Suit.DIAMOND));
		var lesser = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
		assertThat(lesser).isLessThan(hand);
	}

}
