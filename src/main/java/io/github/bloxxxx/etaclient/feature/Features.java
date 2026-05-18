package io.github.bloxxxx.etaclient.feature;

import io.github.bloxxxx.etaclient.feature.builtin.PlayerTracker;
import io.github.bloxxxx.etaclient.feature.builtin.TeleportHandler;
import io.github.bloxxxx.etaclient.feature.impl.LineVarCommandFeature;
import io.github.bloxxxx.etaclient.feature.impl.TestFeature;
import io.github.bloxxxx.etaclient.feature.trait.*;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacket;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacketAnnotationException;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacketRuntimeException;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.PacketHandler;
import net.minecraft.network.packet.Packet;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Features {

    public static void initFeatures() {
        feat(
                // Handler
                new TeleportHandler(),

                // Built-in
                new PlayerTracker(),

                // Normal
                new TestFeature(),
                new LineVarCommandFeature()
        );
    }




























    public static Map<Class<? extends Feature>, Feature> features = new HashMap<>();
    public static List<PacketHandler> packetHandlers = new ArrayList<>();

    public static void init() {
        initFeatures();
        initPacketHandlers();
        callFeatureInit();
    }

    private static void callFeatureInit() {
        // X = Disabled while running
        callFeaturesAlwaysOn(InitFeature.class, InitFeature::init);
        callFeaturesAlwaysOn(CommandFeature.class, CommandFeature::initCommands);
        callFeaturesAlwaysOn(TickedFeature.class, TickedFeature::initTicked);                    // X
        callFeaturesAlwaysOn(HudRenderFeature.class, HudRenderFeature::initRenderHud);           // X
        callFeaturesAlwaysOn(WorldRenderFeature.class, WorldRenderFeature::initWorldRender);     // X
    }

    private static void feat(Feature... features) {
        for (Feature feature : features) {
            registerFeature(feature);
        }
    }

    private static void registerFeature(Feature feature) {
        features.put(feature.getClass(), feature);
    }

    private static void initPacketHandlers() {
        for (Map.Entry<Class<? extends  Feature>, Feature> entry : features.entrySet()) {
            for (Method method : entry.getKey().getDeclaredMethods()) {
                checkForPacketHandler(entry.getValue(), method);
            }
        }
    }

    private static void checkForPacketHandler(Feature feature, Method method) {
        if (!method.isAnnotationPresent(HandlePacket.class)) return;
        if (!Modifier.isStatic(method.getModifiers())) throw getPHException(feature, "Method must be static");
        if (!method.getReturnType().equals(boolean.class)) throw getPHException(feature, "Return type must be a boolean");
        Parameter[] parameters = method.getParameters();
        if (parameters.length != 1) throw getPHException(feature, "Parameter count must be exactly 1");
        Class<?> paramClass = parameters[0].getType();
        if (!Packet.class.isAssignableFrom(paramClass)) throw getPHException(feature, "Parameter must extend net.minecraft.network.packet.Packet");

        MethodHandle handle;
        try {
            handle = MethodHandles.lookup().unreflect(method);
        } catch (IllegalAccessException e) {
            throw getPHException(feature, e.getMessage());
        }

        boolean alwaysOn = method.getAnnotation(HandlePacket.class).alwaysOn();

        packetHandlers.add(new PacketHandler(
                feature,
                (packet) -> {
                    try {
                        return (boolean) handle.invoke(packet);
                    } catch (Throwable throwable) {
                        throw new HandlePacketRuntimeException(throwable);
                    }
                },
                paramClass,
                alwaysOn
        ));

    }

    private static HandlePacketAnnotationException getPHException(Feature feature, String message) {
        return new HandlePacketAnnotationException(message + " in " + feature.getClass().getName());
    }

    public static <T extends Feature> void callSingleFeature(Class<T> featureClass, Consumer<T> action) {
        Feature feature = features.get(featureClass);
        if (feature == null) return;
        if (!feature.enabled()) return;
        T casted = featureClass.cast(feature); // Safer than (T)
        action.accept(casted);
    }

    public static <T extends Feature> void callFeatures(Class<T> featureClass, Consumer<T> action, boolean alwaysEnabled) {
        for (Feature feature : features.values()) {
            if (!feature.enabled() && !alwaysEnabled) continue;
            if (!featureClass.isInstance(feature)) continue;
            T casted = featureClass.cast(feature);
            action.accept(casted);
        }
    }
    public static <T extends Feature> void callFeatures(Class<T> featureClass, Consumer<T> action) {
        callFeatures(featureClass, action, false);
    }
    public static <T extends Feature> void callFeaturesAlwaysOn(Class<T> featureClass, Consumer<T> action) {
        callFeatures(featureClass, action, true);
    }

    public static boolean callPacketHandlers(Packet<?> packet) {
        Class<?> packetClass = packet.getClass();

        boolean cancel = false;
        for (PacketHandler packetHandler : packetHandlers) {
            if (!packetHandler.getParamClass().equals(packetClass)) continue;
            if (!packetHandler.enabled()) continue;
            if (packetHandler.recieve(packet)) cancel = true;
        }
        return cancel;
    }

}
