package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PokerHandTwoPair extends PokerHand {

	public PokerHandTwoPair(Collection<PokerCard> cards) {
		super(PokerHandType.TWO_PAIR, cards);
	}

}
