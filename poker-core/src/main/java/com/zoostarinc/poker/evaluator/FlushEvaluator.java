package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.card.Suit;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class FlushEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		List<Card> suitedCards = null;
		// Group cards by suit
		for (Suit suit : Suit.values()) {
			// Collect all cards of the current suit
			suitedCards = new ArrayList<>();
			// Add cards of the same suit
			cards.stream().filter(card -> card.getSuit() == suit).forEach(suitedCards::add);
			// If collected enough cards then return with Flush Hand
			if (suitedCards.size() >= 5) {
				return new PokerHand(PokerHandType.FLUSH, suitedCards.subList(0, 5));
			}
		}

		return null;
	}

}
