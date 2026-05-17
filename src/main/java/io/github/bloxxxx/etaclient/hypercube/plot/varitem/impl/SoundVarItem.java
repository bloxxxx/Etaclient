package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record SoundVarItem(String sound, double pitch, double volume) implements VarItem {
    public static SoundVarItem fromJson(JsonObject data) {
        return new SoundVarItem(
                JsonUtil.getAsStringDef(data, "sound", ""),
                JsonUtil.getAsDoubleDef(data, "pitch", 1),
                JsonUtil.getAsDoubleDef(data, "vol", 2)
        );
    }
}
