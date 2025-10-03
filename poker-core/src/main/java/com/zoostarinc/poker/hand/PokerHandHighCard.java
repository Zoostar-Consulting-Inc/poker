package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class PokerHandHighCard extends PokerHand {
	
	public PokerHandHighCard(Collection<PokerCard> cards) {
		super(PokerHandType.HIGH_CARD, cards);
	}

}
