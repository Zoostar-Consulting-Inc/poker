package com.zoostarinc.poker.api.response;

import java.util.Collection;

import com.zoostarinc.poker.hand.PokerHand;

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
public class PokerHandComparisonResponse {

	private Collection<PokerHand> hands;
	
}
