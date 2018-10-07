package com.eventify.service.impl;

import com.eventify.models.RuleEngineMaster;
import com.eventify.service.IIftttService1;
import com.eventify.service.IRuleEngineService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineJsonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author praveenkamath
 **/
@Service
public class IftttServiceImpl1 implements IIftttService1<JsonNode> {

    private static final int PAGE_SIZE  =   10;

    @Autowired
    @Qualifier("ruleEngineMasterServiceImpl")
    IRuleEngineService ruleEngineService;

    @Override
    public Mono<List<JsonNode>> getResults(JsonNode facts) {
        Mono<Long> count = ruleEngineService.getN(facts.get("ruleId").asText());
        return count.map(val -> {
            List<JsonNode> ops = Collections.synchronizedList(new ArrayList<>());
            System.out.println(val);
            long totalPages = getTotalPages(val);
            System.out.println(totalPages);
            for(int currPage = 0; currPage < totalPages; currPage++) {
                ruleEngineService.getNMatchesAsync(facts.get("ruleId").asText(), currPage, PAGE_SIZE)
                        .collectList().map(rules -> {
                            System.out.println("time :: "+System.currentTimeMillis() + ", page :: "+rules.size());
                            rules.parallelStream().forEach(rule -> {
                                try {
                                    ops.add(applyAndSave(new ObjectMapper().convertValue(rule.getConditions(), JsonNode.class), facts));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            return true;
                        }).subscribe();
            }
            System.out.println("======");
            return ops;
        });
    }


    @Override
    public Mono<List<JsonNode>> getResultsSync(JsonNode facts) {
        Long val = ruleEngineService.getNSync(facts.get("ruleId").asText());
        List<JsonNode> ops = Collections.synchronizedList(new ArrayList<>());
        long totalPages = getTotalPages(val);
        long sTime = System.currentTimeMillis();
        for(int currPage = 0; currPage < totalPages; currPage++) {
            List<RuleEngineMaster> rules = ruleEngineService.getNMatches(facts.get("ruleId").asText(), currPage, PAGE_SIZE);
            rules.parallelStream().forEach(rule -> {
                try {
                    ops.add(applyAndSave(new ObjectMapper().convertValue(rule.getConditions(), JsonNode.class), facts));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("total time  for "+val+" records :: " + (System.currentTimeMillis() - sTime));
        return Mono.just(ops);
    }

    private JsonNode applyAndSave(JsonNode rule, JsonNode facts) throws Exception {
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
