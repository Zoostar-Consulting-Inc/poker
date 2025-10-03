package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;

public interface PokerHandEvaluator {
	PokerHand evaluate(SortedSet<PokerCard> cards);
}
