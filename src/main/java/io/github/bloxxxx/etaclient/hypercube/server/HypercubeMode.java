package io.github.bloxxxx.etaclient.hypercube.server;

public enum HypercubeMode {
    NONE(HypercubeLocation.NONE),
    TRANSFER(HypercubeLocation.TRANSFER),
    SPAWN(HypercubeLocation.SPAWN),
    PLAY(HypercubeLocation.PLOT),
    BUILD(HypercubeLocation.PLOT),
    DEV(HypercubeLocation.PLOT);

    public final HypercubeLocation location;
    HypercubeMode(HypercubeLocation location) {
        this.location = location;
    }

    public boolean inPlot() {
        return location == HypercubeLocation.PLOT;
    }

    public boolean inSpawn() {
        return location == HypercubeLocation.SPAWN;
    }

    public boolean inTransfer() {
        return location == HypercubeLocation.TRANSFER;
    }

    public boolean inOther() {
        return location == HypercubeLocation.NONE;
    }

    public boolean isDev() {
        return this == HypercubeMode.DEV;
    }

    public boolean isBuild() {
        return this == HypercubeMode.BUILD;
    }

    public boolean isEditing() {
        return isDev() || isBuild();
    }

    public boolean isPlay() {
        return this == HypercubeMode.PLAY;
    }
}
