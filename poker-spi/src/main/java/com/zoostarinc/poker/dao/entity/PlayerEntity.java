package com.zoostarinc.poker.dao.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "PLAYER")
public class PlayerEntity implements Persistable<UUID> {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String email;
	
	private Date previousLogin;

	@Override
	public boolean isNew() {
		return id == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PlayerEntity)) {
			return false;
		}
		PlayerEntity other = (PlayerEntity) obj;
		return Objects.equals(email, other.email);
	}

}
