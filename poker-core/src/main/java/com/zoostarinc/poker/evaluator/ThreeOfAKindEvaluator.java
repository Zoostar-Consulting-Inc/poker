package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class ThreeOfAKindEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		// Track consecutive equal-face cards. Face is an enum, so '==' is safe.
		Card previous = null;
		int sameCount = 0;
		Card[] buffer = new Card[3]; // store up to first 3 matching cards
		var it = cards.iterator();

		while (it.hasNext()) {
			Card current = it.next();

			if (previous == null) {
				previous = current;
				sameCount = 1;
				buffer[0] = current;
				continue;
			}

			if (previous.getFace() == current.getFace()) {
				if (sameCount < 3) {
					buffer[sameCount] = current;
				}
				sameCount++;
				previous = current;
			} else {
				// rank changed: if we already saw >=3 in a row, return the triple
				if (sameCount >= 3) {
					Collection<Card> result = new ArrayList<>(3);
					result.add(buffer[0]);
					result.add(buffer[1]);
					result.add(buffer[2]);
					return new PokerHand(PokerHandType.THREE_OF_A_KIND, result);
				}
				// reset tracking for new rank
				previous = current;
				sameCount = 1;
				buffer[0] = current;
			}
		}

		// final check after iteration
		if (sameCount >= 3) {
			Collection<Card> result = new ArrayList<>(3);
			result.add(buffer[0]);
			result.add(buffer[1]);
			result.add(buffer[2]);
			return new PokerHand(PokerHandType.THREE_OF_A_KIND, result);
		}

		return null;
	}

}