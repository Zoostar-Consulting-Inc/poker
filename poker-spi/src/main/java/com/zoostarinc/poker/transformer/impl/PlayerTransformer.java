package com.zoostarinc.poker.transformer.impl;

import com.zoostarinc.poker.dao.entity.PlayerEntity;
import com.zoostarinc.poker.model.Player;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.transform.Transformer;

@Slf4j
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class PlayerTransformer implements Transformer<Player> {

	private final PlayerEntity entity;
	
	@Override
	public Player transform() {
		log.debug("Transforming player entity to player: {}", entity);
		var player = new Player();
		player.setEmail(entity.getEmail());
		player.setPreviousLogin(entity.getPreviousLogin());
		return player;
	}

}
