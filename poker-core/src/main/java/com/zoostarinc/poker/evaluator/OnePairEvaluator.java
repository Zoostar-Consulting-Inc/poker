package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandOnePair;

public class OnePairEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		PokerHand hand = null;
		PokerCard previous = null;
		int pair = 0;
		var it = cards.iterator();
		Collection<PokerCard> pairCards = new ArrayList<>(DefaultPokerHandEvaluatorChain.MAX_CARDS);
		while (pair < 1 && it.hasNext()) {
			var current = it.next();
			if (previous != null && previous.getFace() == current.getFace()) {
				pairCards.add(current);
				pair++;
			}
			previous = current;
		}

		if (pair > 0) {
			for(var card : cards) {
				if(!pairCards.contains(card)) {
					pairCards.add(card);
				}
			}
			hand = new PokerHandOnePair(pairCards);
		} else {
			hand = null;
		}

		return hand;
	}

}
