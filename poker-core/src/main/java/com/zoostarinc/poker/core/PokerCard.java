package com.zoostarinc.poker.core;

import com.zoostarinc.card.Card;
import com.zoostarinc.card.Face;
import com.zoostarinc.card.Suit;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PokerCard extends Card {

	public PokerCard(Face face, Suit suit) {
		super(face, suit);
	}

}
