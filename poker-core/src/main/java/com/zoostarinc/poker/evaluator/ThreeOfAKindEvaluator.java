package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandThreeOfAKind;

public class ThreeOfAKindEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		PokerHand hand = null;
		PokerCard previous = null;
		int count = 0;
		var it = cards.iterator();
		Collection<PokerCard> threeOfAKind = new ArrayList<>(DefaultPokerHandEvaluatorChain.MAX_CARDS);
		while (count < 2 && it.hasNext()) {
			var current = it.next();
			if (previous != null && previous.getFace() == current.getFace()) {
				threeOfAKind.add(current);
				count++;
			} else {
				count = 0;
			}
			previous = current;
		}

		if (count > 1) {
			for(var card : cards) {
				if(!threeOfAKind.contains(card)) {
					threeOfAKind.add(card);
				}
			}
			hand = new PokerHandThreeOfAKind(threeOfAKind);
		} else {
			hand = null;
		}

		return hand;
	}

}
