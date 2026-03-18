package com.zoostarinc.poker.transformer.impl;

import java.time.LocalDateTime;

import com.zoostarinc.poker.dao.entity.PlayerEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.Utils;
import net.zoostar.common.transform.Transformer;

@Slf4j
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class PlayerEmailTransformer implements Transformer<PlayerEntity> {

	private final String email;
	
	@Override
	public PlayerEntity transform() {
		Utils.assertNotEmpty(email, "Email may not be empty!");
		var player = new PlayerEntity();
		player.setEmail(email);
		player.setPreviousLogin(LocalDateTime.now());
		return player;
	}

}
