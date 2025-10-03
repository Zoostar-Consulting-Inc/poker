package com.zoostarinc.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Card implements Comparable<Card> {

	private final Face face;

	private final Suit suit;

	@Override
	public int compareTo(Card that) {
		if (that.getFace().compareTo(this.getFace()) == 0) {
			return that.getSuit().compareTo(this.getSuit());
		}
		return that.getFace().compareTo(this.getFace());
	}

}
