package com.ifttt.iftttservice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author praveenkamath
 **/
public class JsonUtils {

    public static JsonNode getDefaultNode(int status, String msg) {
        JsonNode jNode = new ObjectNode(JsonNodeFactory.instance);
        ((ObjectNode) jNode).put("status", status);
        ((ObjectNode) jNode).put("message", msg);
        return jNode;
    }
}
