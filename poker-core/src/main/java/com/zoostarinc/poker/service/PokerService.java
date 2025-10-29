package com.zoostarinc.poker.service;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;

public interface PokerService {
	PokerHand evaluate(Collection<PokerCard> cards);
}
