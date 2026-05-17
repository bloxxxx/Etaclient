package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record TagVarItem(String option, String name) implements VarItem {
    public static TagVarItem fromJson(JsonObject data) {
        return new TagVarItem(
                JsonUtil.getAsStringDef(data, "option", ""),
                JsonUtil.getAsStringDef(data, "tag", "")
        );
    }
}
