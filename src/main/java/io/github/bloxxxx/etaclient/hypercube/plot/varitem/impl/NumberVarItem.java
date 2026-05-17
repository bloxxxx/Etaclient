package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record NumberVarItem(double value) implements VarItem {

    public static NumberVarItem fromJson(JsonObject data) {
        return new NumberVarItem(
                JsonUtil.getAsDoubleDef(data, "name", 0)
        );
    }
}
