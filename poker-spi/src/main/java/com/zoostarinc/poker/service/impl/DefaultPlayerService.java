package com.zoostarinc.poker.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoostarinc.poker.dao.entity.PlayerEntity;
import com.zoostarinc.poker.dao.repository.PlayerRepository;
import com.zoostarinc.poker.model.Player;
import com.zoostarinc.poker.service.PlayerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.Utils;
import net.zoostar.common.transform.Transformer;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPlayerService implements PlayerService {

	private final PlayerRepository playerRepository;
	
	@Override
	@Cacheable("playerManager.create")
	public Player create(Transformer<PlayerEntity> transformer) {
		var entity = playerRepository.save(transformer.transform());
		log.info("Created new player: {}", entity);
		var model = new Player(entity.getEmail());
		model.setPreviousLogin(entity.getPreviousLogin());
		return model;
	}

	@Override
	@Cacheable("playerManager.retrieveByEmail")
	@Transactional(readOnly = true)
	public Player retrieveByEmail(String email) {
		Utils.assertNotEmpty(email, "Required field <email> may not be empty!");
		
		var entity = playerRepository.findByEmail(email);
		if(entity.isEmpty()) {
			throw new IllegalArgumentException(String.format("No Player found for email %s", email));
		}
		
		log.info("Found existing player: {}", entity);
		var player = entity.get();
		Player model = new Player(player.getEmail());
		model.setPreviousLogin(player.getPreviousLogin());
		return model;
	}

}
