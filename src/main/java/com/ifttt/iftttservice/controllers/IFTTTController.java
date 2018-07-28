package com.ifttt.iftttservice.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.ifttt.iftttservice.models.RuleEngineMaster;
import com.ifttt.iftttservice.service.IRuleEngineService;
import com.ifttt.iftttservice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
@RestController
public class IFTTTController {

    @Autowired
    @Qualifier("ruleEngineServiceImpl")
    IRuleEngineService ruleEngineService;

    @RequestMapping(value = "/api/ifttt/v1/apply", method = RequestMethod.POST)
    public Mono<JsonNode> apply(@RequestBody JsonNode facts) {
        System.out.println(facts);
        return Mono.just(JsonUtils.getDefaultNode(200, "sss"));
    }

    @RequestMapping(value = "/api/ifttt/v1/nMatch", method = RequestMethod.POST)
    public Flux<RuleEngineMaster> nMatch(@RequestBody JsonNode facts) {
        System.out.println(facts);
        return ruleEngineService.getNMatches(facts.get("nMatchId").asText());
        //return Mono.just(JsonUtils.getDefaultNode(200, "sss"));
    }
}