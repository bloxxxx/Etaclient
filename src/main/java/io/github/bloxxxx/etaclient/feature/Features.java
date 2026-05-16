package io.github.bloxxxx.etaclient.feature;

import io.github.bloxxxx.etaclient.feature.impl.TestFeature;
import io.github.bloxxxx.etaclient.feature.trait.Feature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.*;
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
                // Built-in
                // ...

                // Normal
                new TestFeature()
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
        callFeatures(InitFeature.class, InitFeature::init);
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
                (Class<? extends Packet<?>>) paramClass,
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

    public static <T extends Feature> void callFeatures(Class<T> featureClass, Consumer<T> action) {
        for (Feature feature : features.values()) {
            if (!feature.enabled()) continue;
            if (!featureClass.isInstance(feature)) continue;
            T casted = featureClass.cast(feature);
            action.accept(casted);
        }
    }

    public static boolean callPacketHandlers(Packet<?> packet) {
        Class<? extends Packet> packetClass = packet.getClass();

        boolean cancel = false;
        for (PacketHandler packetHandler : packetHandlers) {
            if (!packetHandler.getParamClass().equals(packetClass)) continue;
            if (!packetHandler.enabled()) continue;
            if (packetHandler.recieve(packet)) cancel = true;
        }
        return cancel;
    }

}
