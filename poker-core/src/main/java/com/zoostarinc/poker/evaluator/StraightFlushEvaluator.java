package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandStraightFlush;

public class StraightFlushEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		int count = 0;
		Collection<PokerCard> straightFlushCards = new ArrayList<>();
		
		// Group cards by suit
		for (Suit suit : Suit.values()) {
			// Add cards of the same suit
			cards.stream().filter(card -> card.getSuit() == suit).forEach(straightFlushCards::add);
			// If collected enough cards then quit the loop
			if (straightFlushCards.size() >= 5) {
				PokerCard previous = null;
				for (var card : straightFlushCards) {
					if (previous != null && card.getFace().compareTo(previous.getFace()) == -1) {
						count++;
					} else {
						count = 0;
					}
					previous = card;
				}
			} else {
				straightFlushCards.clear();
			}
		}

		return count >= 4 ? new PokerHandStraightFlush(straightFlushCards) : null;

	}

}
