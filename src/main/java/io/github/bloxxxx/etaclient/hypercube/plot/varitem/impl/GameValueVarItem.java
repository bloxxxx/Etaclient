package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.ActionTarget;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record GameValueVarItem(String type, ActionTarget target) implements VarItem {
    public static GameValueVarItem fromJson(JsonObject data) {
        return new GameValueVarItem(
                JsonUtil.getAsStringDef(data, "type", ""),
                ActionTarget.fromId(JsonUtil.getAsStringDef(data, "target", ""))
        );
    }
}
