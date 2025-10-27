package com.zoostarinc.poker.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zoostarinc.card.Deck;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardPokerDeck implements Deck<PokerCard> {

	private List<PokerCard> cards;

	public StandardPokerDeck() {
		this.cards = new ArrayList<>();
		for(Suit suit : Suit.values()) {
			for(Face face : Face.values()) {
				cards.add(new PokerCard(face, suit));
			}
		}
	}
	
	@Override
	public List<PokerCard> shuffle() {
		Collections.shuffle(cards);
		return cards;
	}

}
