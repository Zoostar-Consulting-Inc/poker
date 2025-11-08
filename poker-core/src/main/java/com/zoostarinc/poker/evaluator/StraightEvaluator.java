package com.zoostarinc.poker.evaluator;

import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.Utils;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class StraightEvaluator implements PokerHandEvaluator {

	public static final int MIN_HAND_CARD_SIZE = 5;

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		PokerHand hand = null;

		var straight = Utils.getStraightCards(cards);
		if (straight.size() >= 5) {
			hand = new PokerHand(PokerHandType.STRAIGHT, straight);
		}

		return hand;
	}

}
