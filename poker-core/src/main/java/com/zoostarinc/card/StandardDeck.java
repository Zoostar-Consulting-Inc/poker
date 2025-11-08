package com.zoostarinc.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardDeck implements Deck {

	private List<Card> cards;

	public StandardDeck() {
		this.cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Face face : Face.values()) {
				cards.add(new Card(face, suit));
			}
		}
	}

	@Override
	public List<Card> shuffle() {
		Collections.shuffle(cards);
		return cards;
	}

}
