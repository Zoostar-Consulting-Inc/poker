package com.zoostarinc.poker.service;

import com.zoostarinc.poker.dao.entity.PlayerEntity;
import com.zoostarinc.poker.model.Player;

import net.zoostar.common.transform.Transformer;

public interface PlayerService {
	Player create(Transformer<PlayerEntity> transformer);
	Player retrieve(String email);
}
