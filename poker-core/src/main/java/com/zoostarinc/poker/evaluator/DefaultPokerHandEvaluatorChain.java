package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import org.springframework.util.CollectionUtils;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.hand.PokerHand;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DefaultPokerHandEvaluatorChain implements PokerHandEvaluatorChain {
	
	public static final int MAX_CARDS = 7;

	public static final String ILLEGAL_ARG_EXCEPTION_MSG = "Expected minimum of 1 and maximum of 7 cards only!";

	public static final PokerHandEvaluatorChain INSTANCE = new DefaultPokerHandEvaluatorChain();

	private final List<PokerHandEvaluator> evaluators;

	private DefaultPokerHandEvaluatorChain() {
		this.evaluators = new ArrayList<>();
		this.evaluators.add(new RoyalFlushEvaluator());
		this.evaluators.add(new StraightFlushEvaluator());
		this.evaluators.add(new FourOfAKindEvaluator());
		this.evaluators.add(new FullHouseEvaluator());
		this.evaluators.add(new FlushEvaluator());
		this.evaluators.add(new StraightEvaluator());
		this.evaluators.add(new ThreeOfAKindEvaluator());
		this.evaluators.add(new TwoPairEvaluator());
		this.evaluators.add(new OnePairEvaluator());
	}

	@Override
	public PokerHand evaluate(SortedSet<PokerCard> cards) {
		if (CollectionUtils.isEmpty(cards) || cards.size() > MAX_CARDS) {
			throw new IllegalArgumentException(ILLEGAL_ARG_EXCEPTION_MSG);
		}

		var unmodifiableCards = Collections.unmodifiableSortedSet(cards);
		PokerHand hand = null;
		var it = evaluators.iterator();
		while (it.hasNext() && hand == null) {
			var evaluator = it.next();
			log.info("Evaluating if hand is a {}", evaluator);
			hand = evaluator.evaluate(unmodifiableCards);
		}
		
		if(hand == null) {
			hand = new HighCardEvaluator().evaluate(unmodifiableCards);
		}
		
		log.info("Evaluated hand: {}", hand);
		return hand;
	}

}
