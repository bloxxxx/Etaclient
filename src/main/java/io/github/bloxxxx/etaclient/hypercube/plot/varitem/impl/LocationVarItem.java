package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import net.minecraft.util.math.Vec3d;

public record LocationVarItem(Vec3d position, double yaw, double pitch, boolean isBlock) implements VarItem {
    public static LocationVarItem fromJson(JsonObject data) {
        JsonObject loc = data.getAsJsonObject("loc");

        return new LocationVarItem(
                new Vec3d(
                        JsonUtil.getAsDoubleDef(loc, "x", 0),
                        JsonUtil.getAsDoubleDef(loc, "y", 0),
                        JsonUtil.getAsDoubleDef(loc, "z", 0)
                ),
                JsonUtil.getAsDoubleDef(loc, "yaw", 0),
                JsonUtil.getAsDoubleDef(loc, "pitch", 0),
                JsonUtil.getAsBooleanDef(data, "isBlock", false)
        );
    }
}
