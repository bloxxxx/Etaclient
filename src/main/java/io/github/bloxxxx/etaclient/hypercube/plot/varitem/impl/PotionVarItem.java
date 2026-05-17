package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record PotionVarItem(String effect, double duration, double amplifier) implements VarItem {

    public static PotionVarItem fromJson(JsonObject data) {
        return new PotionVarItem(
                JsonUtil.getAsStringDef(data, "pot", ""),
                JsonUtil.getAsDoubleDef(data, "dur", 0),
                JsonUtil.getAsDoubleDef(data, "amp", 0)
        );
    }
}
