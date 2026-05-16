package io.github.bloxxxx.etaclient;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Etaclient implements ClientModInitializer {

    public static String MODID = "etaclient";
    public static Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static MinecraftClient MC = MinecraftClient.getInstance();
    public static Identifier getIdentifier(String name) {
        return Identifier.of(MODID, name);
    }

    @Override
    public void onInitializeClient() {

    }
}
