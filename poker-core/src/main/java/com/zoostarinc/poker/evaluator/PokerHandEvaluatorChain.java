package com.zoostarinc.poker.evaluator;

import java.util.Collection;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;

public interface PokerHandEvaluatorChain {
	PokerHand evaluate(Collection<Card> cards);
}
