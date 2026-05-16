package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;

public record SoundVarItem(String sound, double pitch, double volume) implements VarItem {
    public SoundVarItem(JsonObject data) {
        this(
                data.get("sound").getAsJsonPrimitive().getAsString(),
                data.get("pitch").getAsJsonPrimitive().getAsDouble(),
                data.get("vol").getAsJsonPrimitive().getAsDouble()
        );
    }
}
