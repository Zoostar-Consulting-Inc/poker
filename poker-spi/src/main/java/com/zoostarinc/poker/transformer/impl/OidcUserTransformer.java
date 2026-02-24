package com.zoostarinc.poker.transformer.impl;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.StringUtils;

import com.zoostarinc.poker.model.Player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.zoostar.common.transform.Transformer;

@Getter
@RequiredArgsConstructor
public class OidcUserTransformer implements Transformer<Player> {

	private final OidcUser user;
	
	@Override
	public Player transform() {
		if(user == null || !StringUtils.hasText(user.getEmail())) {
			throw new IllegalArgumentException("User email is requesred!");
		}
		return new Player(user.getEmail());
	}

}
