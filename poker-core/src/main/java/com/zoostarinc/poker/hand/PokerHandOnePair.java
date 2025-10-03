package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class PokerHandOnePair extends PokerHand {
	
	public PokerHandOnePair(Collection<PokerCard> cards) {
		super(PokerHandType.ONE_PAIR, cards);
	}

}
