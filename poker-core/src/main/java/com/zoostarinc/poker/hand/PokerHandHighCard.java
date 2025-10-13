package com.zoostarinc.poker.hand;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PokerHandHighCard extends PokerHand {

	public PokerHandHighCard(Collection<PokerCard> cards) {
		super(PokerHandType.HIGH_CARD, cards);
	}

}
