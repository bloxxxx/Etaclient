package io.github.bloxxxx.etaclient.util;

import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;
import net.kyori.adventure.platform.modcommon.impl.client.MinecraftClientAudiencesImpl;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.text.Text;

public final class MinimsgUtil {
    private MinimsgUtil() {}

    private static MiniMessage mm = MiniMessage.miniMessage();
    private static MinecraftClientAudiences audiences = MinecraftClientAudiencesImpl.INSTANCE;

    public static Component deserialize(String input) {
        return mm.deserialize(input);
    }
    public static Text deserializeText(String input) {
        return audiences.asNative(deserialize(input));
    }

    public static String serialize(Component input) {
        return mm.serialize(input);
    }
    public static String serializeText(Text input) {
        return serialize(audiences.asAdventure(input));
    }


}
