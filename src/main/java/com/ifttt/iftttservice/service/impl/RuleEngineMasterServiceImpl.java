package com.ifttt.iftttservice.service.impl;

import com.ifttt.iftttservice.dao.IRuleEngineRepository;
import com.ifttt.iftttservice.models.RuleEngineMaster;
import com.ifttt.iftttservice.service.IRuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author praveenkamath
 **/
@Service("ruleEngineMasterServiceImpl")
public class RuleEngineMasterServiceImpl implements IRuleEngineService {

    @Autowired
    IRuleEngineRepository ruleEngineRepository;

    @Override
    public Mono<Long> getN(String nMatchId) {
        return ruleEngineRepository.countByMatchId(nMatchId);
    }

    @Override
    public Flux<RuleEngineMaster> getNMatches(String nMatchId, int pageNumber, int pageSize) {
        System.out.println("Page number :: "+pageNumber);
        return ruleEngineRepository.findByMatchId(nMatchId, PageRequest.of(pageNumber, pageSize));
    }
}
