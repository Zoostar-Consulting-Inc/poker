package com.zoostarinc.poker;

import java.util.SortedSet;
import java.util.TreeSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;

public final class Utils {

	public static final int EQUAL = 0;
	public static final int CONSECUTIVE = 1;
	
	private Utils() {
		// Helper class
	}

	public static SortedSet<Card> getFlushCards(SortedSet<Card> cards) {
		SortedSet<Card> flush = new TreeSet<>();
		for (Suit suit : Suit.values()) {
			cards.stream().filter(card -> card.getSuit().equals(suit)).forEach(flush::add);
			if (!flush.isEmpty() && flush.size() < 5) {
				flush.clear();
			}
		}
		return flush;
	}

	public static SortedSet<Card> getStraightCards(SortedSet<Card> cards) {
		Card previous = null;
		Card current = null;
		int result = -1;
		SortedSet<Card> straight = new TreeSet<>();
		var it = cards.iterator();
		Card ace = null;
		while (straight.size() < 4 && it.hasNext()) {
			current = it.next();
			if (current.getFace() == Face.ACE) {
				ace = current;
			}
			if (previous != null && (result = previous.getFace().compareTo(current.getFace())) == CONSECUTIVE) {
				straight.add(previous);
			} else if (result != EQUAL && !straight.isEmpty()) {
				straight.clear();
			}
			previous = current;
		}

		if (result == CONSECUTIVE && straight.size() < 5) {
			straight.add(current);
		}

		if (ace != null && current.getFace() == Face.TWO && straight.size() >= 4) {
			straight.add(ace);
		}

		return straight;
	}

}
