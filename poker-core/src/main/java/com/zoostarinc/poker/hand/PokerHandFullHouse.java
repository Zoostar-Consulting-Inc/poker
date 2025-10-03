package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

public class PokerHandFullHouse extends PokerHand {

	public PokerHandFullHouse(Collection<PokerCard> cards) {
		super(PokerHandType.FULL_HOUSE, cards);
	}

}
