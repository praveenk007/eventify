package com.eventify.service;

import com.eventify.models.RuleEngineMaster;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineService {

    Mono<Long> getN(String nMatchId);

    Long getNSync(String nMatchId);

    Flux<RuleEngineMaster> getNMatchesAsync(String nMatchId, int pageNumber, int pageSize);

    List<RuleEngineMaster> getNMatches(String nMatchId, int pageNumber, int pageSize);
}
