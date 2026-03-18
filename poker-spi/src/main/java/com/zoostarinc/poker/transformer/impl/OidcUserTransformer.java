package com.zoostarinc.poker.transformer.impl;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.zoostarinc.poker.dao.entity.PlayerEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.transform.Transformer;

@Slf4j
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class OidcUserTransformer implements Transformer<PlayerEntity> {

	private final OidcUser user;
	
	@Override
	public PlayerEntity transform() {
		var entity = new PlayerEntity();
		entity.setEmail(user.getEmail());
		log.info("Transformed OidcUser to Player entity: {}", entity);
		return entity;
	}

}
