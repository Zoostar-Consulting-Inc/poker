package com.zoostarinc.poker.hand;

import java.util.TreeSet;

import com.zoostarinc.card.Card;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode(callSuper = true)
public final class PokerHandRoyalFlush extends PokerHand {
	
	public PokerHandRoyalFlush(Suit suit) {
		super(PokerHandType.ROYAL_FLUSH,  new TreeSet<>());
		getCards().add(new Card(Face.ACE, suit));
		getCards().add(new Card(Face.KING, suit));
		getCards().add(new Card(Face.QUEEN, suit));
		getCards().add(new Card(Face.JACK, suit));
		getCards().add(new Card(Face.TEN, suit));
	}

}
