package io.github.bloxxxx.etaclient.util;

import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;
import net.kyori.adventure.platform.modcommon.impl.client.MinecraftClientAudiencesImpl;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public final class MinimsgUtil {
    private MinimsgUtil() {}

    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static final MinecraftClientAudiences audiences = MinecraftClientAudiencesImpl.INSTANCE;

    public static @Nullable Component deserialize(String input) {
        try {
            return mm.deserialize(input);
        } catch (Exception ignored) {
            return null;
        }
    }
    public static @Nullable Text deserializeText(String input) {
        try {
            return audiences.asNative(deserialize(input));
        } catch (Exception ignored) {
            return null;
        }
    }

    public static @Nullable String serialize(Component input) {
        try {
            return mm.serialize(input);
        } catch (Exception ignored) {
            return null;
        }
    }
    public static @Nullable String serializeText(Text input) {
        try {
            return serialize(audiences.asAdventure(input));
        } catch (Exception ignored) {
            return null;
        }
    }


}
