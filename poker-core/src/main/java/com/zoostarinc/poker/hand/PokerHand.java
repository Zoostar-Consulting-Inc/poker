package com.zoostarinc.poker.hand;

import java.util.Collection;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.core.PokerCardComparator;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, // Use a logical name for the type identifier
		include = JsonTypeInfo.As.PROPERTY, // Include the type identifier as a property
		property = "type" // Name of the property containing the type identifier
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = PokerHandHighCard.class, name = "handHighCard")
})
public abstract class PokerHand implements Comparable<PokerHand> {

	public static final int MAX_POKER_CARD_SIZE = 7;

	private PokerHandType type;

	private Comparator<PokerCard> comparator;

	private Collection<PokerCard> cards;

	protected PokerHand(PokerHandType type, Collection<PokerCard> cards) {
		this.type = type;
		this.cards = cards;
		this.comparator = new PokerCardComparator();
	}

	@Override
	public int compareTo(PokerHand that) {
		if (this.getType() == that.getType()) {
			return compare(that.getCards());
		} else {
			return this.getType().compareTo(that.getType());
		}
	}

	protected int compare(Collection<PokerCard> cards) {
		int result = 0;
		var itThis = this.getCards().iterator();
		var itThat = cards.iterator();
		while (itThis.hasNext() && itThat.hasNext()) {
			result = comparator.compare(itThis.next(), itThat.next());
			if (result != 0) {
				return result;
			}
		}
		return result;
	}

}
