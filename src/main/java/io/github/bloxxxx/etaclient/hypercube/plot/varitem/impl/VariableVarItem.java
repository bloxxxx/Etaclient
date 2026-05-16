package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.VariableScope;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;

public record VariableVarItem(String name, VariableScope scope) implements VarItem {
    public VariableVarItem(JsonObject data) {
        this(
                data.get("name").getAsJsonPrimitive().getAsString(),
                VariableScope.fromId(data.get("scope").getAsJsonPrimitive().getAsString())
        );
    }
}
