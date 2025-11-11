package com.zoostarinc.poker;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.EnumMap;
import java.util.ArrayList;
import java.util.List;

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
		// Efficient straight detection:
		// 1) Build a map of Face -> best Card for that face (ignore duplicate faces)
		// 2) Walk the Face enum in order to find consecutive runs of length 5
		// 3) Special-case the wheel (A-2-3-4-5)
		SortedSet<Card> straight = new TreeSet<>();
		if (cards == null || cards.isEmpty()) {
			return straight;
		}

		// Map each face to its highest card (by Card.compareTo)
		EnumMap<Face, Card> faceMap = new EnumMap<>(Face.class);
		for (Card c : cards) {
			Face f = c.getFace();
			Card prev = faceMap.get(f);
			if (prev == null || c.compareTo(prev) > 0) {
				faceMap.put(f, c);
			}
		}

		// Build ordered list of present faces
		List<Face> present = new ArrayList<>();
		for (Face f : Face.values()) {
			if (faceMap.containsKey(f)) {
				present.add(f);
			}
		}

		if (present.isEmpty()) {
			return straight;
		}

		int consec = 1;
		int startIndex = 0;
		for (int i = 1; i < present.size(); i++) {
			Face prevFace = present.get(i - 1);
			Face curFace = present.get(i);
			if (prevFace.ordinal() + 1 == curFace.ordinal()) {
				consec++;
			} else {
				consec = 1;
				startIndex = i;
			}
			if (consec >= 5) {
				// collect the 5-card straight
				straight.clear();
				for (int j = i - 4; j <= i; j++) {
					straight.add(faceMap.get(present.get(j)));
				}
				return straight;
			}
		}

		// Check for wheel (A-2-3-4-5): need A present and 2-5 present consecutively
		// Find if faces TWO, THREE, FOUR, FIVE are present as a consecutive run
		if (faceMap.containsKey(Face.ACE)) {
			boolean hasTwoToFive = true;
			Face[] wheel = { Face.TWO, Face.THREE, Face.FOUR, Face.FIVE };
			for (Face f : wheel) {
				if (!faceMap.containsKey(f)) {
					hasTwoToFive = false;
					break;
				}
			}
			if (hasTwoToFive) {
				straight.clear();
				// Add 2,3,4,5, then Ace as low
				for (Face f : wheel) {
					straight.add(faceMap.get(f));
				}
				straight.add(faceMap.get(Face.ACE));
				return straight;
			}
		}

		return straight;
	}

}