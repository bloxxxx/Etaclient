package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;

public record PotionVarItem(String effect, double duration, double amplifier) implements VarItem {
    public PotionVarItem(JsonObject data) {
        this(
                data.get("pot").getAsJsonPrimitive().getAsString(),
                data.get("dur").getAsJsonPrimitive().getAsDouble(),
                data.get("amp").getAsJsonPrimitive().getAsDouble()
        );
    }
}
