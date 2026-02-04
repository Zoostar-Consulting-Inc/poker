package com.zoostarinc.poker.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.springframework.util.CollectionUtils;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.hand.PokerHandType;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DefaultPokerHandEvaluatorChain implements PokerHandEvaluatorChain {

	public static final int MAX_CARDS_IN_A_HAND = 7;

	public static final String ILLEGAL_ARG_EXCEPTION_MSG = "Expected minimum of 1 and maximum of 7 cards only!";

	public static final PokerHandEvaluatorChain INSTANCE = new DefaultPokerHandEvaluatorChain();

	private final List<PokerHandEvaluator> evaluators;
	
	private int totalCount = 0;

	protected DefaultPokerHandEvaluatorChain() {
		this.evaluators = new ArrayList<>();
		this.evaluators.add(new RoyalFlushEvaluator());
		totalCount++;
		this.evaluators.add(new StraightFlushEvaluator());
		totalCount++;
		this.evaluators.add(new FourOfAKindEvaluator());
		totalCount++;
		this.evaluators.add(new FullHouseEvaluator());
		totalCount++;
		this.evaluators.add(new FlushEvaluator());
		totalCount++;
		this.evaluators.add(new StraightEvaluator());
		totalCount++;
		this.evaluators.add(new ThreeOfAKindEvaluator());
		totalCount++;
		this.evaluators.add(new TwoPairEvaluator());
		totalCount++;
		this.evaluators.add(new OnePairEvaluator());
		totalCount++;
		log.info("Added a total of {} poker hand evaluators.", totalCount);
	}

	@Override
	public PokerHand evaluate(Collection<Card> cards) {
		if (CollectionUtils.isEmpty(cards) || cards.size() > MAX_CARDS_IN_A_HAND) {
			throw new IllegalArgumentException(ILLEGAL_ARG_EXCEPTION_MSG);
		}

		var unmodifiableCards = Collections.unmodifiableSortedSet(new TreeSet<>(cards));
		PokerHand hand = null;
		int count = 0;
		var it = evaluators.iterator();
		while (it.hasNext() && hand == null) {
			var evaluator = it.next();
			count++;
			log.info("Evaluating {}/{} if hand is a {}", count, totalCount, evaluator);
			hand = evaluator.evaluate(unmodifiableCards);
		}

		if (hand == null) {
			hand = new PokerHand(PokerHandType.HIGH_CARD, unmodifiableCards);
		}

		log.info("Evaluated hand: {}", hand);
		return hand;
	}

}
