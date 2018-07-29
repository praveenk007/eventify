package com.ifttt.iftttservice.dao;

import com.ifttt.iftttservice.models.RuleEngineMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineRepository extends ReactiveMongoRepository<RuleEngineMaster, String> {

    Mono<Long> countByMatchId(String nMatchId);

    Flux<RuleEngineMaster> findByMatchId(String nMatchId, Pageable page);
}
