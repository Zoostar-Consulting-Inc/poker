package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

public class PokerHandFourOfAKind extends PokerHand {

	public PokerHandFourOfAKind(Collection<PokerCard> cards) {
		super(PokerHandType.FOUR_OF_A_KIND, cards);
	}

}
