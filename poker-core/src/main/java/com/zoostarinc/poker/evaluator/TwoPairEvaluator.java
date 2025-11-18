package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

public class TwoPairEvaluator implements PokerHandEvaluator {

	@Override
	public PokerHand evaluate(SortedSet<Card> cards) {
		// Need at least four cards to form two pairs
		if (cards.size() < 4) {
			return null;
		}

		int pair = 0;
		Card[][] pairs = new Card[2][2];
		Iterator<Card> it = cards.iterator();
		Card previous = it.next();
		while (it.hasNext()) {
			var current = it.next();
			if (previous.getFace() == current.getFace() && pair < 2) {
				pairs[pair][0] = previous;
				pairs[pair][1] = current;
				pair++;
			}
			previous = current;
		}

		if (pair == 2) {
			List<List<Card>> listOfList = Arrays.stream(pairs).map(Arrays::asList).toList();
			Collection<Card> twoPairs = new ArrayList<>(4);
			for (List<Card> list : listOfList) {
				for (Card card : list) {
					twoPairs.add(card);
				}
			}
			return new PokerHand(PokerHandType.TWO_PAIR, twoPairs);
		}

		return null;
	}

}