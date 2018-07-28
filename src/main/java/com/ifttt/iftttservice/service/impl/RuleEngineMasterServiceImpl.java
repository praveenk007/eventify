package com.ifttt.iftttservice.service.impl;

import com.ifttt.iftttservice.dao.IRuleEngineRepository;
import com.ifttt.iftttservice.models.RuleEngineMaster;
import com.ifttt.iftttservice.service.IRuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author praveenkamath
 **/
@Service("ruleEngineMasterServiceImpl")
public class RuleEngineMasterServiceImpl implements IRuleEngineService {

    @Autowired
    IRuleEngineRepository ruleEngineRepository;

    @Override
    public Flux<RuleEngineMaster> getNMatches(String nMatchId) {
        System.out.println("[getMatches] matchId :: " + nMatchId);
        return ruleEngineRepository.findByMatchId(nMatchId);
    }
}
