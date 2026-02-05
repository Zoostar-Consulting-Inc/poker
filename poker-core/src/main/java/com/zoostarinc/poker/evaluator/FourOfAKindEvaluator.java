package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class FourOfAKindEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		PokerHand hand = null;
		Card previous = null;
		Card current = null;
		var it = cards.iterator();
		Collection<Card> fourOfAKind = new ArrayList<>(DefaultPokerHandEvaluatorChain.MAX_CARDS_IN_A_HAND);
		while (fourOfAKind.size() < 3 && it.hasNext()) {
			current = it.next();
			if (previous != null && previous.getFace() == current.getFace()) {
				fourOfAKind.add(previous);
				previous = current;
			} else {
				fourOfAKind.clear();
				previous = current;
				current = null;
			}
		}

		if (fourOfAKind.size() > 2) {
			if (current != null) {
				fourOfAKind.add(current);
			}
			hand = new PokerHand(PokerHandType.FOUR_OF_A_KIND, fourOfAKind);
		} else {
			hand = null;
		}

		return hand;
	}

}
