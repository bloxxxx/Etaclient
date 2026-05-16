package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.MinimsgUtil;
import net.minecraft.text.Text;

public record TextVarItem(String value) implements VarItem {
    public TextVarItem(JsonObject data) {
        this(data.get("name").getAsJsonPrimitive().getAsString());
    }

    public Text getFormatted() {
        return MinimsgUtil.deserializeText(value);
    }
}
