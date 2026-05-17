package io.github.bloxxxx.etaclient.hypercube.server;

public class HypercubeClientPlayerData {
    public int tokens;
    public int tickets;
    public int sparks;

    public HypercubeClientPlayerData() {
        tokens = 0;
        tickets = 0;
        sparks = 0;
    }

    @Override
    public String toString() {
        return "HypercubeClientPlayerData{" +
                "sparks=" + sparks +
                ", tokens=" + tokens +
                ", tickets=" + tickets +
                '}';
    }
}
