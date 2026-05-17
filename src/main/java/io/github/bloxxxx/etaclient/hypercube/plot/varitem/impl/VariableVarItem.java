package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.VariableScope;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record VariableVarItem(String name, VariableScope scope) implements VarItem {
    public static VariableVarItem fromJson(JsonObject data) {
        return new VariableVarItem(
                JsonUtil.getAsStringDef(data, "name", ""),
                VariableScope.fromId(JsonUtil.getAsStringDef(data, "scope", ""))
        );
    }
}
