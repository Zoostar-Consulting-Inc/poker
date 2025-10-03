package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

public class PokerHandThreeOfAKind extends PokerHand {

	public PokerHandThreeOfAKind(Collection<PokerCard> cards) {
		super(PokerHandType.THREE_OF_A_KIND, cards);
	}

}
