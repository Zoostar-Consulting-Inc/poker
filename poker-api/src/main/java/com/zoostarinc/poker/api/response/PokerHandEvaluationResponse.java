package com.zoostarinc.poker.api.response;

import java.util.Collection;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHandType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PokerHandEvaluationResponse {

	private PokerHandType type;
	
	private Collection<Card> cards;
	
}
