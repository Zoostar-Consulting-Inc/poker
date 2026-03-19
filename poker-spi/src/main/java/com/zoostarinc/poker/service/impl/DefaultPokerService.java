package com.zoostarinc.poker.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.dao.entity.PlayerEntity;
import com.zoostarinc.poker.evaluator.DefaultPokerHandEvaluatorChain;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.model.Player;
import com.zoostarinc.poker.service.PlayerCrudService;
import com.zoostarinc.poker.service.PokerService;
import com.zoostarinc.poker.transformer.impl.OidcUserTransformer;
import com.zoostarinc.poker.transformer.impl.PlayerEmailTransformer;
import com.zoostarinc.poker.transformer.impl.PlayerTransformer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultPokerService implements PokerService {

	private final PlayerCrudService playerCrudService;

	@Override
	public PokerHand evaluate(Collection<Card> cards) {
		return DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards);
	}

	@Override
	public Collection<PokerHand> compare(Collection<Card> cards1, Collection<Card> cards2) {
		Collection<PokerHand> hands = new ArrayList<>();
		var hand1 = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards1);
		var hand2 = DefaultPokerHandEvaluatorChain.INSTANCE.evaluate(cards2);
		int result = hand1.compareTo(hand2);
		if (result == 0) {
			hands.add(hand1);
			hands.add(hand2);
		} else if (result > 0) {
			hands.add(hand1);
		} else {
			hands.add(hand2);
		}

		return hands;
	}

	@Override
	public Player retrieveByEmail(OidcUser user, boolean forceCreate) {
		PlayerEntity entity = null;
		try {
			entity = playerCrudService.retrieveByEmail(user.getEmail());
		} catch (IllegalArgumentException e) {
			if (forceCreate) {
				entity = playerCrudService.create(new OidcUserTransformer(user));
			} else {
				throw e;
			}
		}
		return new PlayerTransformer(entity).transform();
	}

	@Override
	public Player updateLoginTime(String email) {
		return new PlayerTransformer(playerCrudService.update(new PlayerEmailTransformer(email))).transform();
	}

}
