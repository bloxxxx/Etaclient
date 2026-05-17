package io.github.bloxxxx.etaclient.util;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public final class PlayerUtil {
    private PlayerUtil() {}

    public static ClientPlayerEntity get() {
        return McUtil.MC.player;
    }

    public static Optional<ClientPlayerEntity> getOp() {
        return Optional.ofNullable(get());
    }

    public static void sendMessage(Text message, boolean overlay) {
        getOp().ifPresent((player) -> player.sendMessage(message, overlay));
    }
    public static void sendMessage(Text message) {
        sendMessage(message, false);
    }
    public static void sendMessage(String message) {
        sendMessage(Text.literal(message));
    }
    public static void sendMinimsg(String message) {
        sendMessage(MinimsgUtil.deserializeText(message));
    }

    public static Vec3d getPosition() {
        ClientPlayerEntity player = get();
        if (player == null) return Vec3d.ZERO;
        return player.getEntityPos();
    }

    public static void sendCommand(String command) {
        NetworkUtil.getOp().ifPresent((handler) -> handler.sendChatCommand(command));
    }

    public static void teleport(Vec3d pos) {
        getOp().ifPresent((player) -> player.setPosition(pos));
    }
    public static void teleportInstant(Vec3d pos) {
        getOp().ifPresent((player) -> {
            double x = pos.x;
            double y = pos.y;
            double z = pos.z;
            player.setPosition(x, y, z);
            player.lastX = x;
            player.lastY = y;
            player.lastZ = z;
            player.lastRenderX = x;
            player.lastRenderY = y;
            player.lastRenderZ = z;
        });
    }
    public static void teleportServer(Vec3d pos) {
        NetworkUtil.send(new PlayerMoveC2SPacket.PositionAndOnGround(
                    pos,
                    false,
                    false
                )
        );
    }

    public static void setVelocity(Vec3d vel) {
        getOp().ifPresent((player) -> player.setVelocity(vel));
    }

    public static ItemStack getHandStack() {
        ClientPlayerEntity player = get();
        if (player == null) return ItemStack.EMPTY;
        return player.getMainHandStack();
    }
}
