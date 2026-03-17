package com.zoostarinc.poker.service;

import java.util.Collection;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.zoostarinc.card.Card;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.model.Player;

public interface PokerService {
	PokerHand evaluate(Collection<Card> cards);
	Collection<PokerHand> compare(Collection<Card> cards1, Collection<Card> cards2);
	Player retrieveByEmail(OidcUser user, boolean forceCreate);
	Player updateLoginTime(String email);
}
