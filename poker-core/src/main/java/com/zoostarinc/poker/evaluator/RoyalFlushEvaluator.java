package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandRoyalFlush;

public class RoyalFlushEvaluator implements PokerHandEvaluator {

	private static final List<Face> ROYAL_FACES = Arrays.asList(Face.ACE, Face.KING, Face.QUEEN, Face.JACK, Face.TEN);

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		for (Suit suit : Suit.values()) {
			List<Face> facesInSuit = new ArrayList<>();
			for (Card card : cards) {
				if (card.getSuit() == suit) {
					facesInSuit.add(card.getFace());
				}
			}
			if (facesInSuit.containsAll(ROYAL_FACES)) {
				return new PokerHandRoyalFlush(suit);
			}
		}
		return null;
	}

}