package com.zoostarinc.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card implements Comparable<Card> {

	private Face face;

	private Suit suit;

	@Override
	public int compareTo(Card that) {
		int result = that.getFace().compareTo(this.getFace());
		if (result == 0) {
			return that.getSuit().compareTo(this.getSuit());
		}
		return result;
	}

}
