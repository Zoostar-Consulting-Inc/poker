package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class OnePairEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		// Quick fail: need at least two cards to form a pair
		if (cards.size() < 2) {
			return null;
		}

		Iterator<Card> it = cards.iterator();
		Card previous = it.next();
		while (it.hasNext()) {
			Card current = it.next();
			if (previous.getFace() == current.getFace()) {
				var pair = new ArrayList<Card>(2);
				pair.add(previous);
				pair.add(current);
				return new PokerHand(PokerHandType.ONE_PAIR, pair);
			}
			previous = current;
		}

		return null;
	}

}