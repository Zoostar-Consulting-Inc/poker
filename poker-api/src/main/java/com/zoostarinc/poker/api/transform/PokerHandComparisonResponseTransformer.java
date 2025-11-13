package com.zoostarinc.poker.api.transform;

import java.util.Collection;

import com.zoostarinc.poker.api.response.PokerHandComparisonResponse;
import com.zoostarinc.poker.hand.PokerHand;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.zoostar.common.transform.Transformer;

@ToString
@RequiredArgsConstructor
public class PokerHandComparisonResponseTransformer implements Transformer<PokerHandComparisonResponse> {

	private final Collection<PokerHand> hands;
	
	@Override
	public PokerHandComparisonResponse transform() {
		return new PokerHandComparisonResponse(hands);
	}

}
