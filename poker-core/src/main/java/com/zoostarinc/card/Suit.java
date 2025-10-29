package com.zoostarinc.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Suit {
	
	DIAMOND(Color.RED), CLUB(Color.BLACK), HEART(Color.RED), SPADE(Color.BLACK);
	
	private final Color color;
	
	@Override
	public String toString() {
		return this.name();
	}
	
}
