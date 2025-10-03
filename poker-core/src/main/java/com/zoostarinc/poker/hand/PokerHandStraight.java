package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

public class PokerHandStraight extends PokerHand {

	public PokerHandStraight(Collection<PokerCard> cards) {
		super(PokerHandType.STRAIGHT, cards);
	}

}
