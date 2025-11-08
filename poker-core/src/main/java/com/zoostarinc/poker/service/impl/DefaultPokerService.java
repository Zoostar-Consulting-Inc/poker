package com.zoostarinc.poker.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.evaluator.DefaultPokerHandEvaluatorChain;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.service.PokerService;

@Service
public class DefaultPokerService implements PokerService {

	@Override
	public PokerHand evaluate(Collection<Card> cards) {
		return DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
	}

	@Override
	public Collection<PokerHand> compare(Collection<Card> cards1, Collection<Card> cards2) {
		Collection<PokerHand> hands = new ArrayList<>();
		var hand1 = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards1);
		var hand2 = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards2);
		int result = hand1.compareTo(hand2);
		if (result == 0) {
			hands.add(hand1);
			hands.add(hand2);
		} else if (result > 0) {
			hands.add(hand1);
		} else {
			hands.add(hand2);
		}

		return hands;
	}

}
