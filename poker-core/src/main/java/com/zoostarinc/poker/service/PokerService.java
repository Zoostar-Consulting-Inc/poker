package com.zoostarinc.poker.service;

import java.util.Collection;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;

public interface PokerService {
	PokerHand evaluate(Collection<Card> cards);
	Collection<PokerHand> compare(Collection<Card> cards1, Collection<Card> cards2);
}
