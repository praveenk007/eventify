package com.eventify.controllers;

import com.eventify.service.IIftttService1;
import com.eventify.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author praveenkamath
 **/
@RestController
public class IFTTTController {

    @Autowired
    IIftttService1 iftttService;

    @RequestMapping(value = "/api/eventify/v1/apply", method = RequestMethod.POST)
    public Mono<JsonNode> apply(@RequestBody JsonNode facts) {
        System.out.println(facts);
        return Mono.just(JsonUtils.getDefaultNode(200, "sss"));
    }

    @RequestMapping(value = "/api/eventify/v1/nMatch", method = RequestMethod.POST)
    public Mono<List<JsonNode>> nMatch(@RequestBody JsonNode facts) {
        System.out.println(facts);
        return iftttService.getResults(facts);
    }
}