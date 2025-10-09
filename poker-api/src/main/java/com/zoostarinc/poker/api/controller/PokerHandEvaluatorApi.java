package com.zoostarinc.poker.api.controller;

import java.util.SortedSet;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.evaluator.PokerHandEvaluatorChain;
import com.zoostarinc.poker.hand.PokerHand;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PokerHandEvaluatorApi {

	PokerHandEvaluatorChain chain;
	
	@PostMapping(path = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PokerHand> evaluate(@RequestBody SortedSet<PokerCard> cards) {
		return ResponseEntity.ok(chain.evaluate(cards));
	}
}
