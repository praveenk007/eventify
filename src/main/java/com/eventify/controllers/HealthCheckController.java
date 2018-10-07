package com.eventify.controllers;

import com.eventify.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
@RestController
public class HealthCheckController {

    @RequestMapping("/api/eventify/v1/healthcheck")
    public Mono<JsonNode> getStatus() {
        return Mono.just(JsonUtils.getDefaultNode(200, "i'm fine. Thanks for asking :')"));
    }
}
