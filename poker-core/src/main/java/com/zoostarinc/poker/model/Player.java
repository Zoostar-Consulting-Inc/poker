package com.zoostarinc.poker.model;

import java.util.Date;

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
	
	private Date previousLogin;
	
	public Player(String email) {
		this.email = email;
	}
	
}
