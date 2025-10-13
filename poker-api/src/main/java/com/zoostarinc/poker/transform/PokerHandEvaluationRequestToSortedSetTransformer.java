package com.zoostarinc.poker.transform;

import java.util.SortedSet;
import java.util.TreeSet;

import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.core.PokerCard;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.zoostar.common.transform.Transformer;

@ToString
@RequiredArgsConstructor
public class PokerHandEvaluationRequestToSortedSetTransformer implements Transformer<SortedSet<PokerCard>> {

	private final PokerHandEvaluationRequest request;
	
	@Override
	public SortedSet<PokerCard> transform() {
		return new TreeSet<>(request.getCards());
	}

}
