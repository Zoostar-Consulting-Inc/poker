package com.zoostarinc.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum Suit {
	
	DIAMOND(Color.RED), CLUB(Color.BLACK), HEART(Color.RED), SPADE(Color.BLACK);
	
	private final Color color;
	
}
