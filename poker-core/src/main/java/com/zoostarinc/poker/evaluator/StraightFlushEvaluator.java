package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.Utils;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class StraightFlushEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		PokerHand hand = null;
		var flush = Utils.getFlushCards(cards);
		var straight = Utils.getStraightCards(flush);
		if (straight.size() >= 5) {
			hand = new PokerHand(PokerHandType.STRAIGHT_FLUSH, straight);
		}

		return hand;
	}

}
