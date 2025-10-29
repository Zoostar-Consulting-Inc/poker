package com.zoostarinc.poker.api.response;

import java.util.ArrayList;
import java.util.List;

import com.zoostarinc.poker.api.request.PokerHandComparisonRequest;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.service.PokerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.zoostar.common.transform.Transformer;

@Getter
@ToString
@RequiredArgsConstructor
public class PokerHandComparisonResponse implements Transformer<List<PokerHand>> {

	private final PokerService pokerManager;

	private final PokerHandComparisonRequest request;

	@Override
	public List<PokerHand> transform() {
		List<PokerHand> response = new ArrayList<>();
		var hand1 = pokerManager.evaluate(request.getCards1());
		var hand2 = pokerManager.evaluate(request.getCards2());

		int result = hand1.compareTo(hand2);
		if (result > 0) {
			response.add(hand1);
		} else if (result < 0) {
			response.add(hand2);
		} else {
			response.add(hand1);
			response.add(hand2);
		}

		return response;
	}

}
