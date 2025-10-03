package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

public class PokerHandStraightFlush extends PokerHand {

	public PokerHandStraightFlush(Collection<PokerCard> cards) {
		super(PokerHandType.STRAIGHT_FLUSH, cards);
	}

}
