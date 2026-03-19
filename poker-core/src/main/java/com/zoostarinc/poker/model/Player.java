package com.zoostarinc.poker.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Player {

	private String email;
	
	private LocalDateTime previousLogin;
	
	public Player(String email) {
		this.email = email;
	}
	
}
