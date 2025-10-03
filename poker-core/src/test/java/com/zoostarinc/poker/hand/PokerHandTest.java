package com.zoostarinc.poker.hand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;

class PokerHandTest {

	@Test
	void testCompareTo() {
		PokerHand hand1 = new PokerHandHighCard(Arrays.asList(new PokerCard(Face.ACE, Suit.CLUB)));
		PokerHand hand2 = new PokerHandOnePair(
				Arrays.asList(new PokerCard(Face.ACE, Suit.CLUB), new PokerCard(Face.ACE, Suit.DIAMOND)));
		assertThat(hand1).isNotEqualByComparingTo(hand2);
	}

}
