package com.zoostarinc.poker.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoostarinc.poker.dao.entity.PlayerEntity;
import com.zoostarinc.poker.dao.repository.PlayerRepository;
import com.zoostarinc.poker.service.PlayerCrudService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.Utils;
import net.zoostar.common.transform.Transformer;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPlayerCrudService implements PlayerCrudService {

	private final PlayerRepository playerRepository;
	
	@Override
	public PlayerEntity create(Transformer<PlayerEntity> transformer) {
		return playerRepository.save(transformer.transform());
	}

	@Override
	@Cacheable("playerManager.retrieveByEmail")
	@Transactional(readOnly = true)
	public PlayerEntity retrieveByEmail(String email) {
		Utils.assertNotEmpty(email, "Required field <email> may not be empty!");
		
		var entity = playerRepository.findByEmail(email);
		if(entity.isEmpty()) {
			throw new IllegalArgumentException(String.format("No Player found for email %s", email));
		}

		return entity.get();
	}

	@Override
	public PlayerEntity update(Transformer<PlayerEntity> transformer) {
		var player = transformer.transform();
		String email = player.getEmail();
		log.info("Retrieving record for email: {}...", email);
		var object = playerRepository.findByEmail(email);
		if(object.isEmpty()) {
			throw new IllegalArgumentException("No record found for given email!");
		}
		
		var entity = object.get();
		entity.setPreviousLogin(player.getPreviousLogin());
		return entity;
	}

}
