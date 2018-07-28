package com.ifttt.iftttservice.service;

import com.ifttt.iftttservice.models.RuleEngineMaster;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineService {

    Flux<RuleEngineMaster> getNMatches(String nMatchId);
}
