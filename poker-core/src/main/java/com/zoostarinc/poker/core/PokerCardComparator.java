package com.zoostarinc.poker.core;

import java.util.Comparator;

public class PokerCardComparator implements Comparator<PokerCard> {

	@Override
	public int compare(PokerCard o1, PokerCard o2) {
		return o1.getFace().compareTo(o2.getFace());
	}

}
