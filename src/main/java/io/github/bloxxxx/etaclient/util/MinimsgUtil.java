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
            return mm.deserialize(convertVanillaCodeToMinimsg(input));
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

    public static String convertVanillaCodeToMinimsg(String input) {
        return input
                .replaceAll("§0", "<black>")
                .replaceAll("§1", "<dark_blue>")
                .replaceAll("§2", "<dark_green>")
                .replaceAll("§3", "<dark_aqua>")
                .replaceAll("§4", "<dark_red>")
                .replaceAll("§5", "<dark_purple>")
                .replaceAll("§6", "<gold>")
                .replaceAll("§7", "<gray>")
                .replaceAll("§8", "<dark_gray>")
                .replaceAll("§9", "<blue>")
                .replaceAll("§a", "<green>")
                .replaceAll("§b", "<aqua>")
                .replaceAll("§c", "<red>")
                .replaceAll("§d", "<light_purple>")
                .replaceAll("§e", "<yellow>")
                .replaceAll("§f", "<white>")

                .replaceAll("§k", "<obfuscated>")
                .replaceAll("§l", "<bold>")
                .replaceAll("§m", "<strikethrough>")
                .replaceAll("§n", "<underlined>")
                .replaceAll("§o", "<italic>")
                .replaceAll("§r", "<reset>")
                .replaceAll("§.", "");
    }

    public static String removeVanillaCode(String input) {
        return input.replaceAll("§.", "");
    }


}
