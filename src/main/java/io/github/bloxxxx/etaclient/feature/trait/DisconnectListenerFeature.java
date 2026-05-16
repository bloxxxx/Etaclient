package io.github.bloxxxx.etaclient.feature.trait;

import net.minecraft.network.DisconnectionInfo;

public interface DisconnectListenerFeature extends Feature {
    void onDisconnect(DisconnectionInfo info);
}
