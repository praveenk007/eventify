package com.eventify.dao;

import com.eventify.models.RuleEngineMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * @author praveenkamath
 **/
public interface IRuleEngineMasterRepo extends MongoRepository<RuleEngineMaster, String> {

    List<RuleEngineMaster> findByMatchId(String nMatchId, Pageable page);

    Long countByMatchId(String nMatchId);
}
