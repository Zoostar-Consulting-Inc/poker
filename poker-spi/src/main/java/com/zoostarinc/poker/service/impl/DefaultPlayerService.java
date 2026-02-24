package com.zoostarinc.poker.service.impl;

import org.springframework.stereotype.Service;

import com.zoostarinc.poker.model.Player;
import com.zoostarinc.poker.service.PlayerService;

import net.zoostar.common.transform.Transformer;

@Service
public class DefaultPlayerService implements PlayerService {

	@Override
	public Player create(Transformer<Player> transformer) {
		return transformer.transform();
	}

}
