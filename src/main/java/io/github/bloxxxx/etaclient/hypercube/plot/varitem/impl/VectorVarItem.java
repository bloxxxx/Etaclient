package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import net.minecraft.util.math.Vec3d;

public record VectorVarItem(Vec3d value) implements VarItem {
    public VectorVarItem(JsonObject data) {
        this(new Vec3d(
                data.get("x").getAsJsonPrimitive().getAsDouble(),
                data.get("y").getAsJsonPrimitive().getAsDouble(),
                data.get("z").getAsJsonPrimitive().getAsDouble()
        ));
    }
}
