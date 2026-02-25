package com.zoostarinc.poker.dao.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoostarinc.poker.dao.entity.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity , UUID> {
	Optional<PlayerEntity> findByEmail(String email);
}