package com.zoostarinc.poker.evaluator;

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
		Map<Face, Integer> fullHouse = new EnumMap<>(Face.class);
		var it = cards.iterator();
		while (it.hasNext()) {
			var card = it.next();
			fullHouse.computeIfAbsent(card.getFace(), k -> Integer.valueOf(0));
			fullHouse.put(card.getFace(), fullHouse.get(card.getFace()) + 1);
		}
		
		if(fullHouse.containsValue(Integer.valueOf(3)) && fullHouse.containsValue(Integer.valueOf(2))) {
			return new PokerHandFullHouse(cards);
		}
		
		return null;
	}
	
}
