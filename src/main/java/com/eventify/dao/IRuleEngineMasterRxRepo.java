package com.eventify.dao;

import com.eventify.models.RuleEngineMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineMasterRxRepo extends ReactiveMongoRepository<RuleEngineMaster, String> {

    Mono<Long> countByMatchId(String nMatchId);

    Flux<RuleEngineMaster> findByMatchId(String nMatchId, Pageable page);
}
