package com.eventify.service;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author praveenkamath
 **/
public interface IEventifyService {

    void eventify(String eventId, JsonNode data, JsonNode facts) throws Exception;
}
