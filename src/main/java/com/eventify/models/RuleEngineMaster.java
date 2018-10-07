package com.eventify.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @author praveenkamath
 **/
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="RuleEngineMaster")
public class RuleEngineMaster {

    @Id
    private String id;
    private String matchId;
    private String vertical;
    private String returnType;
    Map<String, Object> conditions;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "RuleEngineMaster{" +
                "matchId='" + matchId + '\'' +
                ", vertical='" + vertical + '\'' +
                ", returnType='" + returnType + '\'' +
                ", id='" + id + '\'' +
                ", conditions=" + conditions +
                '}';
    }
}
