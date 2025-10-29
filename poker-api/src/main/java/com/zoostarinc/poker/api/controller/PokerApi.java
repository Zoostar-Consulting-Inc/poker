package com.zoostarinc.poker.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoostarinc.poker.api.request.PokerHandComparisonRequest;
import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.api.response.PokerHandComparisonResponse;
import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.api.transform.PokerHandEvaluationRequestToSortedSetTransformer;
import com.zoostarinc.poker.api.transform.PokerHandToPokerHandEvaluationResponseTransformer;
import com.zoostarinc.poker.core.PokerCard;
import com.zoostarinc.poker.core.StandardPokerDeck;
import com.zoostarinc.poker.hand.PokerHand;
import com.zoostarinc.poker.service.PokerService;

import lombok.RequiredArgsConstructor;
import net.zoostar.common.audit.Timeable;
import net.zoostar.common.web.response.SuccessfulRequestLoggerResponseEntity;

@Timeable
@RestController
@RequiredArgsConstructor
public class PokerApi {

	final ObjectMapper om;

	final PokerService pokerManager;

	@GetMapping(path = "/shuffle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PokerCard>> shuffle() {
		var deck = new StandardPokerDeck();
		return ResponseEntity.ok(deck.shuffle());
	}

	@PostMapping(path = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PokerHandEvaluationResponse> evaluate(@RequestBody PokerHandEvaluationRequest request) {
		return new SuccessfulRequestLoggerResponseEntity<>(new PokerHandToPokerHandEvaluationResponseTransformer(
				pokerManager.evaluate(new PokerHandEvaluationRequestToSortedSetTransformer(request).transform()))
				.transform(), request, om);
	}

	@PostMapping(path = "/compare", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PokerHand>> compare(@RequestBody PokerHandComparisonRequest request) {
		return new SuccessfulRequestLoggerResponseEntity<>(
				new PokerHandComparisonResponse(pokerManager, request).transform(), request, om);
	}

}
