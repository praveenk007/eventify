package com.eventify.service.impl;

import com.eventify.dao.IRuleEngineMasterRepo;
import com.eventify.models.RuleEngineMaster;
import com.eventify.service.IEventifyService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineJsonImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author praveenkamath
 **/
public class EventifyServiceImpl implements IEventifyService {

    @Autowired
    IRuleEngineMasterRepo ruleEngineMasterRepo;

    @Override
    public void eventify(String eventId, JsonNode data, JsonNode facts) throws Exception {
        RuleEngineMaster rule = ruleEngineMasterRepo.findById(eventId).get();
        JsonNode action = iff(new ObjectMapper().convertValue(rule.getConditions(), JsonNode.class), facts);
        if(action != null)
            then(action, data);
    }

    private JsonNode iff(JsonNode trigger, JsonNode facts) throws Exception {
        JsonNode jsonNode = new RuleEngineJsonImpl().runThis(trigger).against(facts).execute();
        if(jsonNode != null) {
            return jsonNode.get("op");
        } return null;
    }

    private void then(JsonNode action, JsonNode data) {
        String actionFx = action.get("action").asText();
    }
}
