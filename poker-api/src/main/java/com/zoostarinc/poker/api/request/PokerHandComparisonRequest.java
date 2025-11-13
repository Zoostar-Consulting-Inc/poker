package com.zoostarinc.poker.api.request;

import java.util.List;

import com.zoostarinc.card.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PokerHandComparisonRequest {

	private List<Card> cards1;

	private List<Card> cards2;
	
}
