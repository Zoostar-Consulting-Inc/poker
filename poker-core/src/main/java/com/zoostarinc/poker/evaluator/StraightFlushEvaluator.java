package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;

import com.zoostarinc.poker.Utils;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandStraightFlush;

public class StraightFlushEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		PokerHand hand = null;
		var flush = Utils.getFlushCards(cards);
		var straight = Utils.getStraightCards(flush);
		if (straight.size() >= 5) {
			hand = new PokerHandStraightFlush(straight);
		}

		return hand;
	}

}
