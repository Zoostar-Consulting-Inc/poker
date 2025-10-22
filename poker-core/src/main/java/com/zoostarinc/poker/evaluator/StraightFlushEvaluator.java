package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandStraightFlush;

public class StraightFlushEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		Collection<PokerCard> flush = null;
		Collection<PokerCard> straightFlush = new ArrayList<>(5);
		PokerHand hand = null;
		PokerCard current = null;
		PokerCard ace = null;
		int result = -1;

		// Group cards by suit
		for (Suit suit : Suit.values()) {
			// Collect all cards of the current suit
			flush = new ArrayList<>();
			// Add cards of the same suit
			cards.stream().filter(card -> card.getSuit() == suit).forEach(flush::add);
			// If collected enough cards then quit the loop
			if (flush.size() >= 5) {
				PokerCard previous = null;
				var it = flush.iterator();
				while (straightFlush.size() < 5 && it.hasNext()) {
					current = it.next();
					if (current.getFace() == Face.ACE) {
						ace = current;
					}
					if (previous != null && (result = previous.getFace().compareTo(current.getFace()) - 1) == 0) {
						straightFlush.add(previous);
					} else {
						straightFlush.clear();
					}
					previous = current;
				}
			} else {
				flush.clear();
			}
		}

		if (result == 0 && straightFlush.size() < 5) {
			straightFlush.add(current);
		}

		if (straightFlush.size() >= 5) {
			hand = new PokerHandStraightFlush(straightFlush);
		} else if (straightFlush.size() >= 4 && ace != null && current.getFace() == Face.TWO) {
			straightFlush.add(ace);
			hand = new PokerHandStraightFlush(straightFlush);
		}

		return hand;
	}

}
