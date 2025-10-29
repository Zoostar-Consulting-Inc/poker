package com.zoostarinc.poker.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.evaluator.DefaultPokerHandEvaluatorChain;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.service.PokerService;

@Service
public class DefaultPokerService implements PokerService {

	@Override
	public PokerHand evaluate(Collection<PokerCard> cards) {
		return DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
	}

}
