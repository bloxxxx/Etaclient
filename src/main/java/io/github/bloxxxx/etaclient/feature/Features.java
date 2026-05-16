package io.github.bloxxxx.etaclient.feature;

import io.github.bloxxxx.etaclient.feature.impl.TestFeature;
import io.github.bloxxxx.etaclient.feature.trait.Feature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;

import java.util.HashMap;
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

    public static void init() {
        initFeatures();
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

}
