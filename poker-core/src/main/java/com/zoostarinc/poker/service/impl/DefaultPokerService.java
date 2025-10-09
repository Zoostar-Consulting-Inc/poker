package com.zoostarinc.poker.service.impl;

import java.util.SortedSet;

import org.springframework.stereotype.Service;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.evaluator.DefaultPokerHandEvaluatorChain;
import com.zoostarinc.poker.evaluator.PokerHandEvaluatorChain;
import com.zoostarinc.poker.hand.PokerHand;

@Service
public class DefaultPokerService implements PokerHandEvaluatorChain {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		return DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
	}

}
