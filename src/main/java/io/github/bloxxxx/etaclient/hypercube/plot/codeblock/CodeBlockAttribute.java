package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public enum CodeBlockAttribute {
    NONE,
    NOT,
    LS_CANCEL;

    public static CodeBlockAttribute fromId(String id) {
        return switch (id) {
            case "NOT" -> NOT;
            case "LS-CANCEL" -> LS_CANCEL;
            case null, default -> NONE;
        };
    }

    public static CodeBlockAttribute fromJson(JsonObject root) {
        return CodeBlockAttribute.fromId(JsonUtil.getAsString(root, "attribute"));
    }
}
