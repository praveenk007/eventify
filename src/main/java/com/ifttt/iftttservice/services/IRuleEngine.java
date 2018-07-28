package com.ifttt.iftttservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
public interface IRuleEngine {

    Mono<JsonNode> applyRule(JsonNode facts);

    Mono<JsonNode> nMatch(JsonNode facts);
}
