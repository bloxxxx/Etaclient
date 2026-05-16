package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.util.math.Vec3d;

public record LocationVarItem(Vec3d position, double yaw, double pitch, boolean isBlock) implements VarItem {
    public LocationVarItem(JsonObject data) {
        this(
                new Vec3d(
                        getLoc(data, "x"),
                        getLoc(data, "y"),
                        getLoc(data, "z")
                ),
                getLoc(data, "yaw"),
                getLoc(data, "pitch"),
                data.get("isBlock").getAsJsonPrimitive().isBoolean()
        );

        PlayerUtil.sendMessage(data.toString());
    }

    private static double getLoc(JsonObject data, String key) {
        return data.getAsJsonObject("loc").get(key).getAsJsonPrimitive().getAsDouble();
    }
}
