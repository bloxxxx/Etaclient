package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.ActionTarget;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;

public record GameValueVarItem(String type, ActionTarget target) implements VarItem {
    public GameValueVarItem(JsonObject data) {
        this(
                data.get("type").getAsJsonPrimitive().getAsString(),
                ActionTarget.fromId(data.get("target").getAsJsonPrimitive().getAsString())
        );
    }
}
