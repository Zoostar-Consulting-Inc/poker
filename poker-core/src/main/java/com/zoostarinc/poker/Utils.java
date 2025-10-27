package com.zoostarinc.poker;

import java.util.SortedSet;
import java.util.TreeSet;

import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.core.PokerCard;

public final class Utils {

	private Utils() {
		// Helper class
	}

	public static SortedSet<PokerCard> getFlushCards(SortedSet<PokerCard> cards) {
		SortedSet<PokerCard> flush = new TreeSet<>();
		for (Suit suit : Suit.values()) {
			cards.stream().filter(card -> card.getSuit().equals(suit)).forEach(flush::add);
			if (!flush.isEmpty() && flush.size() < 5) {
				flush.clear();
			}
		}
		return flush;
	}

	public static SortedSet<PokerCard> getStraightCards(SortedSet<PokerCard> cards) {
		PokerCard previous = null;
		PokerCard current = null;
		int result = -1;
		SortedSet<PokerCard> straight = new TreeSet<>();
		var it = cards.iterator();
		PokerCard ace = null;
		while (straight.size() < 4 && it.hasNext()) {
			current = it.next();
			if (current.getFace() == Face.ACE) {
				ace = current;
			}
			if (previous != null && (result = previous.getFace().compareTo(current.getFace()) - 1) == 0) {
				straight.add(previous);
			} else {
				straight.clear();
			}
			previous = current;
		}

		if (result == 0 && straight.size() < 5) {
			straight.add(current);
		}

		if (ace != null && current.getFace() == Face.TWO && straight.size() >= 4) {
			straight.add(ace);
		}

		return straight;
	}

}
