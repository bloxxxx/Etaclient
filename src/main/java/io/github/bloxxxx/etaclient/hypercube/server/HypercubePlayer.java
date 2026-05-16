package io.github.bloxxxx.etaclient.hypercube.server;

import com.mojang.authlib.GameProfile;

import java.util.UUID;

public record HypercubePlayer(GameProfile profile) {
    public String name() {
        return profile.name();
    }
    public UUID uuid() {
        return profile.id();
    }
}
