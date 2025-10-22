package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandFourOfAKind;

public class FourOfAKindEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		PokerHand hand = null;
		PokerCard previous = null;
		PokerCard current = null;
		var it = cards.iterator();
		Collection<PokerCard> fourOfAKind = new ArrayList<>(DefaultPokerHandEvaluatorChain.MAX_CARDS);
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
			hand = new PokerHandFourOfAKind(fourOfAKind);
		} else {
			hand = null;
		}

		return hand;
	}

}
