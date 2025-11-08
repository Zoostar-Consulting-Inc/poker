package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class OnePairEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		PokerHand hand = null;
		Card previous = null;
		var it = cards.iterator();
		Collection<Card> pairCards = new ArrayList<>(DefaultPokerHandEvaluatorChain.MAX_CARDS);
		while (pairCards.size() < 2 && it.hasNext()) {
			var current = it.next();
			if (previous != null && previous.getFace() == current.getFace()) {
				pairCards.add(previous);
				pairCards.add(current);
			}
			previous = current;
		}

		if (pairCards.size() > 1) {
			hand = new PokerHand(PokerHandType.ONE_PAIR, pairCards);
		} else {
			hand = null;
		}

		return hand;
	}

}
