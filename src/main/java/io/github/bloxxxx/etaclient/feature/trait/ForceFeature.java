package io.github.bloxxxx.etaclient.feature.trait;

public interface ForceFeature extends Feature {
    @Override
    default boolean enabled() {
        return true;
    }
}
