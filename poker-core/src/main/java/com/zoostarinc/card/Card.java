package com.zoostarinc.card;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
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

	@Override
	public int hashCode() {
		return Objects.hash(face, suit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		Card other = (Card) obj;
		return face == other.face && suit == other.suit;
	}

}
