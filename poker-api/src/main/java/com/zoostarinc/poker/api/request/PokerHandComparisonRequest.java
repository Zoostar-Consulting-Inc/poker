package com.zoostarinc.poker.api.request;

import java.util.List;

import com.zoostarinc.poker.core.PokerCard;

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

	private List<PokerCard> cards1;

	private List<PokerCard> cards2;
	
}
