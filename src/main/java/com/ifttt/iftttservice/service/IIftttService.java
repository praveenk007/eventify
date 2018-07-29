package com.ifttt.iftttservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;

/**
 * @author praveenkamath
 **/
public interface IIftttService<T> {

    Flux<T> getResults(JsonNode facts);
}
