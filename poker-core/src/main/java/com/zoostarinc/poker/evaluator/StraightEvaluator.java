package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;
import java.util.TreeSet;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandStraight;

public class StraightEvaluator implements PokerHandEvaluator {

	public static final int MIN_HAND_CARD_SIZE = 5;

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		PokerHand hand = null;
		PokerCard previous = null;
		PokerCard current = null;
		int result = -1;
		var straight = new TreeSet<PokerCard>();
		var it = cards.iterator();
		while (straight.size() < 5 && it.hasNext()) {
			current = it.next();
			if (previous != null && (result = current.compareTo(previous) - 1) == 0) {
				straight.add(previous);
			} else {
				straight.clear();
			}
			previous = current;
		}

		if (result == 0) {
			straight.add(current);
		}

		if (straight.size() >= 5) {
			hand = new PokerHandStraight(straight);
		}

		return hand;
	}

}
