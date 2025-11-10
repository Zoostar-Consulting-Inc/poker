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
		PokerHand hand = null;
		Card previous = null;
		Card current = null;
		var it = cards.iterator();
		Collection<Card> threeOfAKind = new ArrayList<>(DefaultPokerHandEvaluatorChain.MAX_CARDS);
		while (threeOfAKind.size() < 2 && it.hasNext()) {
			current = it.next();
			if (previous != null && previous.getFace() == current.getFace()) {
				threeOfAKind.add(previous);
				previous = current;
			} else {
				threeOfAKind.clear();
				previous = current;
				current = null;
			}
		}

		if (threeOfAKind.size() > 1) {
			if (current != null) {
				threeOfAKind.add(current);
			}
			hand = new PokerHand(PokerHandType.THREE_OF_A_KIND, threeOfAKind);
		} else {
			hand = null;
		}

		return hand;
	}

}
