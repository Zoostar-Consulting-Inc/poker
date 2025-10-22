package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.SortedSet;

import com.zoostarinc.card.Face;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandFullHouse;

public class FullHouseEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		Map<Face, Integer> cardCountMap = new EnumMap<>(Face.class);
		var it = cards.iterator();
		while (it.hasNext()) {
			var card = it.next();
			cardCountMap.computeIfAbsent(card.getFace(), k -> Integer.valueOf(0));
			cardCountMap.put(card.getFace(), cardCountMap.get(card.getFace()) + 1);
		}

		Collection<PokerCard> fullHouse = new ArrayList<>(5);
		it = cards.iterator();
		while (it.hasNext()) {
			var card = it.next();
			var count = cardCountMap.get(card.getFace());
			if (count > 1) {
				fullHouse.add(card);
			}
		}

		if (cardCountMap.containsValue(Integer.valueOf(3)) && cardCountMap.containsValue(Integer.valueOf(2))) {
			return new PokerHandFullHouse(fullHouse);
		}

		return null;
	}

}
