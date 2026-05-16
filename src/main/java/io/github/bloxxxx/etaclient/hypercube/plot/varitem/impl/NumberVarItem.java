package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;

public record NumberVarItem(double value) implements VarItem {

    public NumberVarItem(JsonObject data) {
        this(data.get("name").getAsJsonPrimitive().getAsDouble());
    }
}
