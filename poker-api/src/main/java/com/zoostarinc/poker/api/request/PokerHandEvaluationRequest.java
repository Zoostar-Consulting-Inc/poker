package com.zoostarinc.poker.api.request;

import java.util.Collection;

import com.zoostarinc.poker.core.PokerCard;

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
public class PokerHandEvaluationRequest {

	private Collection<PokerCard> cards;
	
}
