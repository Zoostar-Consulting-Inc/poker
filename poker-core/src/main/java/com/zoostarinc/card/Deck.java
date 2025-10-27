package com.zoostarinc.card;

import java.util.List;

import net.zoostar.common.Shuffleable;

public interface Deck<T extends Card<T>> extends Shuffleable<T> {
	
	List<T> getCards();
	
}
