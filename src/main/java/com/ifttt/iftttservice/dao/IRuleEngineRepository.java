package com.ifttt.iftttservice.dao;

import com.ifttt.iftttservice.models.RuleEngineMaster;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineRepository extends ReactiveMongoRepository<RuleEngineMaster, String> {

    @Query("{'matchId' : 'MAXBUPA_PREMIUM_BLOCK_BASIC'}")
    Flux<RuleEngineMaster> findByMatchId(String nMatchId);
}
