package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;

public interface PokerHandEvaluator {
	PokerHand evaluate(SortedSet<Card> cards);
}
