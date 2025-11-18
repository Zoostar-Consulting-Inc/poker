package com.zoostarinc.poker.hand;

import java.util.Collection;
import java.util.Objects;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.core.PokerCardComparator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@ToString
@NoArgsConstructor
public class PokerHand implements Comparable<PokerHand> {

	public static final int MAX_POKER_CARD_SIZE = 7;

	private final PokerCardComparator comparator = new PokerCardComparator();

	@Getter
	private PokerHandType type;

	@Getter
	private Collection<Card> cards;

	public PokerHand(PokerHandType type, Collection<Card> cards) {
		this.type = type;
		this.cards = cards;
	}

	@Override
	public int compareTo(PokerHand that) {
		if (this.getType() == that.getType()) {
			return compare(that.getCards());
		} else {
			return this.getType().compareTo(that.getType());
		}
	}

	protected int compare(Collection<Card> cards) {
		int result = 0;
		var itThis = this.getCards().iterator();
		var itThat = cards.iterator();
		int i = 0;
		while (itThis.hasNext() && itThat.hasNext() && i++ < 5) {
			result = comparator.compare(itThis.next(), itThat.next());
			if (result != 0) {
				return result;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PokerHand)) {
			return false;
		}
		PokerHand other = (PokerHand) obj;
		return Objects.equals(cards, other.cards) && type == other.type;
	}

}
