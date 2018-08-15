package com.ifttt.iftttservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ifttt.abstracts.impl.RuleEngineJsonImpl;
import com.ifttt.iftttservice.models.RuleEngineMaster;
import com.ifttt.iftttservice.service.IIftttService;
import com.ifttt.iftttservice.service.IRuleEngineService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author praveenkamath
 **/
@Service
public class IftttServiceImpl implements IIftttService<JsonNode> {

    private static final int PAGE_SIZE  =   10;

    @Autowired
    @Qualifier("ruleEngineMasterServiceImpl")
    IRuleEngineService ruleEngineService;

    @Override
    public Flux<JsonNode> getResults(JsonNode facts) {
        Mono<Long> count = ruleEngineService.getN(facts.get("ruleId").asText());
        count.subscribe(val -> {
            System.out.println("count :: "+val);
            long totalPages = getTotalPages(val);
            System.out.println("total pages :: " + totalPages);
            for(int currPage = 0; currPage < totalPages; currPage++) {
                ruleEngineService.getNMatches(facts.get("ruleId").asText(), currPage, PAGE_SIZE)
                        .collectList()
                        .map(rules -> {
                            System.out.println("time :: "+System.currentTimeMillis() + ", page :: "+rules.size());
                            return true;
                        }).subscribe();
            }
        });
        return null;
    }

    private JsonNode applyRule(JsonNode rule, JsonNode facts) throws Exception {
        if(rule == null || facts == null) {
            return null;
        }
        JsonNode op = new RuleEngineJsonImpl().runThis(rule).against(facts).execute();
        System.out.println("op :: " + op);
        return op;
    }

    private long getTotalPages(Long val) {
        if(val % PAGE_SIZE == 0) {
            return val / PAGE_SIZE;
        } return (val / PAGE_SIZE) + 1;
    }
}
