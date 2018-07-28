package com.ifttt.iftttservice.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.ifttt.iftttservice.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
@RestController
public class HealthCheckController {

    @RequestMapping("/api/ifttt/v1/healthcheck")
    public Mono<JsonNode> getStatus() {
        return Mono.just(JsonUtils.getDefaultNode(200, "i'm fine. Thanks for asking :')"));
    }
}
