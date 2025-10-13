package com.zoostarinc.poker.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zoostarinc.poker.api.request.PokerHandEvaluationRequest;
import com.zoostarinc.poker.api.response.PokerHandEvaluationResponse;
import com.zoostarinc.poker.api.transform.PokerHandEvaluationRequestToSortedSetTransformer;
import com.zoostarinc.poker.api.transform.PokerHandToPokerHandEvaluationResponseTransformer;
import com.zoostarinc.poker.evaluator.PokerHandEvaluatorChain;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PokerHandEvaluatorApi {

	PokerHandEvaluatorChain chain;

	@PostMapping(path = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PokerHandEvaluationResponse> evaluate(@RequestBody PokerHandEvaluationRequest request) {
		return ResponseEntity.ok(new PokerHandToPokerHandEvaluationResponseTransformer(
				chain.evaluate(new PokerHandEvaluationRequestToSortedSetTransformer(request).transform())).transform());
	}

}
