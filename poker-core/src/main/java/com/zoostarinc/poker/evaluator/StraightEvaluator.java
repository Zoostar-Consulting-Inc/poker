package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.SortedSet;

import com.zoostarinc.card.Face;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandStraight;

public class StraightEvaluator implements PokerHandEvaluator {

	public static final int MIN_HAND_CARD_SIZE = 5;

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		PokerHand hand = null;
		PokerCard previous = null;
		PokerCard current = null;
		int result = -1;
		var straight = new ArrayList<PokerCard>();
		var it = cards.iterator();
		PokerCard ace = null;
		while (straight.size() < 5 && it.hasNext()) {
			current = it.next();
			if (current.getFace() == Face.ACE) {
				ace = current;
			}
			if (previous != null && (result = previous.getFace().compareTo(current.getFace()) - 1) == 0) {
				straight.add(previous);
			} else {
				straight.clear();
			}
			previous = current;
		}

		if (result == 0 && straight.size() < 5) {
			straight.add(current);
		}
		
		if(straight.size() >= 5) {
			hand = new PokerHandStraight(straight);
		} else if (straight.size() >= 4 && ace != null && current.getFace() == Face.TWO) {
			straight.add(ace);
			hand = new PokerHandStraight(straight);
		}

		return hand;
	}

}
