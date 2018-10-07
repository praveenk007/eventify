package com.eventify.service.impl;

import com.eventify.dao.IRuleEngineMasterRepo;
import com.eventify.dao.IRuleEngineMasterRxRepo;
import com.eventify.models.RuleEngineMaster;
import com.eventify.service.IRuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * @author praveenkamath
 **/
@Service("ruleEngineMasterServiceImpl")
public class RuleEngineMasterServiceImpl implements IRuleEngineService {

    @Autowired
    IRuleEngineMasterRxRepo ruleEngineRepositoryRx;

    @Autowired
    IRuleEngineMasterRepo ruleEngineRepository;

    @Override
    public Long getNSync(String nMatchId) {
        return ruleEngineRepository.countByMatchId(nMatchId);
    }

    @Override
    public Mono<Long> getN(String nMatchId) {
        return ruleEngineRepositoryRx.countByMatchId(nMatchId);
    }

    @Override
    public Flux<RuleEngineMaster> getNMatchesAsync(String nMatchId, int pageNumber, int pageSize) {
        System.out.println("Page number :: "+pageNumber);
        return ruleEngineRepositoryRx.findByMatchId(nMatchId, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<RuleEngineMaster> getNMatches(String nMatchId, int pageNumber, int pageSize) {
        System.out.println("Page number :: "+pageNumber);
        return ruleEngineRepository.findByMatchId(nMatchId, PageRequest.of(pageNumber, pageSize));
    }
}
