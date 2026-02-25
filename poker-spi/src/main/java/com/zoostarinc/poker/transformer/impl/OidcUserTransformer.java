package com.zoostarinc.poker.transformer.impl;

import java.util.Date;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.StringUtils;

import com.zoostarinc.poker.dao.entity.PlayerEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.transform.Transformer;

@Slf4j
@Getter
@RequiredArgsConstructor
public class OidcUserTransformer implements Transformer<PlayerEntity> {

	private final OidcUser user;
	
	@Override
	public PlayerEntity transform() {
		if(user == null || !StringUtils.hasText(user.getEmail())) {
			throw new IllegalArgumentException("User email is requesred!");
		}
		
		var entity = new PlayerEntity();
		entity.setEmail(user.getEmail());
		entity.setPreviousLogin(new Date());
		log.info("Transformed OidcUser to Player entity: {}", entity);
		return entity;
	}

}
