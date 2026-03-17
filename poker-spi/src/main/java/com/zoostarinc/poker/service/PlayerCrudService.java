package com.zoostarinc.poker.service;

import com.zoostarinc.poker.dao.entity.PlayerEntity;

import net.zoostar.common.transform.Transformer;

public interface PlayerCrudService {
	PlayerEntity create(Transformer<PlayerEntity> transformer);
	PlayerEntity retrieveByEmail(String email);
	PlayerEntity update(Transformer<PlayerEntity> transformer);
}
