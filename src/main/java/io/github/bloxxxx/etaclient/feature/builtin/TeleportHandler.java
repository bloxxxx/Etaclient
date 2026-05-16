package io.github.bloxxxx.etaclient.feature.builtin;

import io.github.bloxxxx.etaclient.Etaclient;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.TickedFeature;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacket;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

import java.util.ArrayList;
import java.util.List;

public class TeleportHandler implements TickedFeature, ForceFeature {

    private static int awaitingSounds = 0;
    private static int awaitingSoundsTimer = 0;

    private static int MAX_TP_LENGTH = 15;
    private static double MAX_DIFF = 0.1;
    private static int SOUND_AWAIT_TIMER = 10;

    public static boolean tp(Vec3d goal) {
        ClientPlayerEntity player = PlayerUtil.get();
        if (player == null) return false;
        GameMode gameMode = player.getGameMode();

        Vec3d start = PlayerUtil.getPosition();
        Vec3d difference = goal.subtract(start);
        double dist = difference.length();
        Vec3d step = difference.normalize();

        Vec3d lastPosition = start;
        List<Vec3d> tpPositions = new ArrayList<>();

        boolean foundSolution = false;
        for (int x = 0; x < MAX_TP_LENGTH; x++) {
            foundSolution = false;
            boolean foundGoal = false;
            for (int i = 8; i > 0; i--) {
                Vec3d pos = lastPosition.add(step.multiply(i));

                // Overshot
                if (start.distanceTo(pos) > dist) {
                    pos = goal;
                }
                if (pos.distanceTo(goal) < MAX_DIFF) {
                    foundGoal = true;
                }

                if (!isAir(pos)) {
                    if (gameMode.isSurvivalLike()) {
                        break;
                    }
                    if (gameMode != GameMode.SPECTATOR) continue;
                };
                foundSolution = true;

                lastPosition = pos;
                tpPositions.add(pos);
                break;
            }
            if (!foundSolution) break;
            if (foundGoal) break;
        }

        if (!foundSolution || tpPositions.size() > MAX_TP_LENGTH || (!isAir(goal) && gameMode != GameMode.SPECTATOR)) {
            if (PlayerTracker.MODE.isEditing()) {
                PlayerUtil.sendCommand("plot teleport " + goal.getX() + " " + goal.getY() + " " + goal.getZ());
                awaitingSounds++;
                awaitingSoundsTimer = SOUND_AWAIT_TIMER;
                return true;
            }
            return false;
        }

        for (Vec3d pos : tpPositions) {
            PlayerUtil.teleportServer(pos);
        }
        PlayerUtil.teleportInstant(goal);
        PlayerUtil.setVelocity(Vec3d.ZERO);

        return true;

    }
    public static boolean tp(BlockPos goal) {
        return tp(goal.toCenterPos());
    }

    private static boolean isAir(Vec3d pos) {
        ClientPlayerEntity player = PlayerUtil.get();
        if (player == null) return false;
        ClientWorld world = Etaclient.MC.world;
        if (world == null) return false;

        Box box = player.getBoundingBox()
                .offset(pos.subtract(PlayerUtil.getPosition()));

        return world.isSpaceEmpty(player, box);
    }

    @HandlePacket
    public static boolean handleSound(PlaySoundFromEntityS2CPacket packet) {
        if (packet.getSound().getIdAsString().equals("minecraft:entity.enderman.teleport") && awaitingSounds > 0) {
            decrementSounds();
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        if (awaitingSoundsTimer > 0) {
            awaitingSoundsTimer--;
            if (awaitingSoundsTimer == 0 && awaitingSounds > 0) {
                decrementSounds();
            }
        }
    }

    private static void decrementSounds() {
        awaitingSoundsTimer = SOUND_AWAIT_TIMER;
        awaitingSounds--;
    }
}
