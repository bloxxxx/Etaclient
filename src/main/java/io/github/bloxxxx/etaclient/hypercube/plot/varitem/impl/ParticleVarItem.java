package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import net.minecraft.util.math.Vec3d;

public record ParticleVarItem(String particle, int amount, double horizontalCluster, double verticalCluster, OptionalData optional) implements VarItem {

    public static ParticleVarItem fromJson(JsonObject data) {

        JsonObject cluster = JsonUtil.getAsJsonObjectDefEmpty(data, "cluster");
        JsonObject opData = JsonUtil.getAsJsonObjectDefEmpty(data, "data");

        return new ParticleVarItem(
                JsonUtil.getAsStringDef(data, "particle", ""),
                JsonUtil.getAsIntDef(cluster, "amount", 1),
                JsonUtil.getAsDoubleDef(cluster, "horizontal", 0),
                JsonUtil.getAsDoubleDef(cluster, "vertical", 0),
                new OptionalData(
                        new Vec3d(
                                JsonUtil.getAsDoubleDef(opData, "x", 0),
                                JsonUtil.getAsDoubleDef(opData, "y", 0),
                                JsonUtil.getAsDoubleDef(opData, "z", 0)
                        ),
                        JsonUtil.getAsIntDef(opData, "motionVariation", 0),
                        JsonUtil.getAsStringDef(opData, "material", ""),
                        JsonUtil.getAsIntDef(opData, "rgb", 0),
                        JsonUtil.getAsIntDef(opData, "colorVariation", 0),
                        JsonUtil.getAsDoubleDef(opData, "power", 0),
                        JsonUtil.getAsIntDef(opData, "opacity", 0),
                        JsonUtil.getAsDoubleDef(opData, "size", 0),
                        JsonUtil.getAsIntDef(opData, "sizeVariation", 0),
                        JsonUtil.getAsIntDef(opData, "rgb_fade", 0),
                        JsonUtil.getAsIntDef(opData, "time", 0),
                        JsonUtil.getAsDoubleDef(opData, "roll", 0)
                )
        );
    }

    public record OptionalData(
            Vec3d motion,
            int motionVariation,
            String material,
            int color,
            int colorVariation,
            double power,
            int opacity,
            double size,
            int sizeVariation,
            int fadeColor,
            int duration,
            double roll
    ) {

    }
}
