package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.VariableType;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record ParameterVarItem(String name, VariableType type, boolean plural, boolean optional, VarItem defaultValue) implements VarItem {
    public static ParameterVarItem fromJson(JsonObject data) {
        return new ParameterVarItem(
                JsonUtil.getAsStringDef(data, "name", ""),
                VariableType.fromId(JsonUtil.getAsStringDef(data, "type", "")),
                JsonUtil.getAsBooleanDef(data, "plural", false),
                JsonUtil.getAsBooleanDef(data, "optional", false),
                VarItem.parse(JsonUtil.getAsJsonObjectDefEmpty(data, "default_value"))
        );
    }
}
