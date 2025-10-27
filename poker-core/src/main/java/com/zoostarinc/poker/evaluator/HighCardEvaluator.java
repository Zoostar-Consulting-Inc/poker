package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandHighCard;

public class HighCardEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		return new PokerHandHighCard(cards);
	}

}
