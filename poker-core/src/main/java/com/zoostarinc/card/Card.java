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
public class Card<T extends Card<T>> implements Comparable<T> {

	private Face face;

	private Suit suit;

	@Override
	public int compareTo(T that) {
		if (that.getFace().compareTo(this.getFace()) == 0) {
			return that.getSuit().compareTo(this.getSuit());
		}
		return that.getFace().compareTo(this.getFace());
	}

}
