package com.zoostarinc.poker.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoostarinc.card.Card;
import com.zoostarinc.card.StandardDeck;
import com.zoostarinc.poker.api.request.PokerHandComparisonRequest;
import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.api.response.PokerHandComparisonResponse;
import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.api.transform.PokerHandComparisonResponseTransformer;
import com.zoostarinc.poker.api.transform.PokerHandToPokerHandEvaluationResponseTransformer;
import com.zoostarinc.poker.service.PokerService;

import lombok.RequiredArgsConstructor;
import net.zoostar.common.audit.Timeable;
import net.zoostar.common.web.response.SuccessfulRequestLoggerResponseEntity;

@Timeable
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PokerApi {

	final ObjectMapper om;

	final PokerService pokerManager;

	@GetMapping(path = "/shuffle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Card>> shuffle() {
		var deck = new StandardDeck();
		return ResponseEntity.ok(deck.shuffle());
	}

	@PostMapping(path = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PokerHandEvaluationResponse> evaluate(@RequestBody PokerHandEvaluationRequest request) {
		return new SuccessfulRequestLoggerResponseEntity<>(
				new PokerHandToPokerHandEvaluationResponseTransformer(pokerManager.evaluate(request.getCards()))
						.transform(),
				request, om);
	}

	@PostMapping(path = "/compare", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PokerHandComparisonResponse> compare(@RequestBody PokerHandComparisonRequest request) {
		return new SuccessfulRequestLoggerResponseEntity<>(new PokerHandComparisonResponseTransformer(
				pokerManager.compare(request.getCards1(), request.getCards2())).transform(), request, om);
	}

}
