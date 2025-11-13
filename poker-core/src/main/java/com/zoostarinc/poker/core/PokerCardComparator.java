package com.zoostarinc.poker.core;

import java.util.Comparator;

import com.zoostarinc.card.Card;

public class PokerCardComparator implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		return o1.getFace().compareTo(o2.getFace());
	}

}
