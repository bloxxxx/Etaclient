package io.github.bloxxxx.etaclient.feature.impl;

import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacket;
import io.github.bloxxxx.etaclient.util.LogUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;

public class TestFeature implements InitFeature, ForceFeature {

    @Override
    public void init() {
        LogUtil.log("TestFeature Initialized");
    }

    @HandlePacket
    public static boolean handleCommandC2S(CommandExecutionC2SPacket packet) {
        PlayerUtil.sendMessage("Sent command: " + packet.command());
        return false;
    }
}
