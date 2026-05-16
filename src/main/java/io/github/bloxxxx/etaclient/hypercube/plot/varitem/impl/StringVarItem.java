package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;

public record StringVarItem(String value) implements VarItem {
    public StringVarItem(JsonObject data) {
        this(data.get("name").getAsJsonPrimitive().getAsString());
    }
}
