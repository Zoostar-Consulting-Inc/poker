package com.zoostarinc.poker.api.transform;

import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.hand.PokerHand;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.zoostar.common.transform.Transformer;

@ToString
@RequiredArgsConstructor
public class PokerHandToPokerHandEvaluationResponseTransformer implements Transformer<PokerHandEvaluationResponse> {

	private final PokerHand pokerHand;

	@Override
	public PokerHandEvaluationResponse transform() {
		var response = new PokerHandEvaluationResponse();
		response.setCards(pokerHand.getCards());
		response.setType(pokerHand.getType());
		return response;
	}

}
