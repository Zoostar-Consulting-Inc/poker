package com.zoostarinc.card;

import java.util.List;

import net.zoostar.common.Shuffleable;

public interface Deck extends Shuffleable<Card> {
	
	List<Card> getCards();
	
}
