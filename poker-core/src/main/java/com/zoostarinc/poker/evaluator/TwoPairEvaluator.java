package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class TwoPairEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		// Need at least four cards to form two pairs
		if (cards == null || cards.size() < 4) {
			return null;
		}

		Iterator<Card> it = cards.iterator();
		Card previous = it.next();
		List<Card> pairCards = null; // allocate only when we find a pair

		while (it.hasNext() && (pairCards == null || pairCards.size() < 4)) {
			Card current = it.next();
			if (previous.getFace() == current.getFace()) {
				if (pairCards == null) {
					pairCards = new ArrayList<>(4);
				}
				// add the first two cards of this face-run as the pair
				pairCards.add(previous);
				pairCards.add(current);

				// skip any remaining cards of the same face (avoid overlapping pairs)
				while (it.hasNext()) {
					Card next = it.next();
					if (next.getFace() != current.getFace()) {
						previous = next;
						break;
					}
					// continue skipping cards with same face
				}
			} else {
				previous = current;
			}
		}

		if (pairCards != null && pairCards.size() == 4) {
			return new PokerHand(PokerHandType.TWO_PAIR, pairCards);
		}

		return null;
	}

}