package com.eventify.service;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author praveenkamath
 **/
public interface IIftttService1<T> {

    Mono<List<JsonNode>> getResults(JsonNode facts);

    Mono<List<JsonNode>> getResultsSync(JsonNode facts);
}
