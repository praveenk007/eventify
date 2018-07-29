package com.ifttt.iftttservice.service;

import com.ifttt.iftttservice.models.RuleEngineMaster;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineService {

    Mono<Long> getN(String nMatchId);

    Flux<RuleEngineMaster> getNMatches(String nMatchId, int pageNumber, int pageSize);
}
