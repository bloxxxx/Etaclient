package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import net.minecraft.util.math.Vec3d;

public record VectorVarItem(Vec3d value) implements VarItem {
    public static VectorVarItem fromJson(JsonObject data) {
        return new VectorVarItem(new Vec3d(
                JsonUtil.getAsDoubleDef(data, "x", 0),
                JsonUtil.getAsDoubleDef(data, "y", 0),
                JsonUtil.getAsDoubleDef(data, "z", 0)
        ));
    }
}
