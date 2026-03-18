package com.zoostarinc.poker.dao.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "PLAYER")
public class PlayerEntity implements Persistable<UUID> {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@EqualsAndHashCode.Include
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "PREVIOUS_LOGIN")
	private LocalDateTime previousLogin;

	@Override
	public boolean isNew() {
		log.info("{} is new?", this);
		return id == null;
	}

}
