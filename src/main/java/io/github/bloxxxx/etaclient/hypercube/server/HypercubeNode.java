package io.github.bloxxxx.etaclient.hypercube.server;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class HypercubeNode {

    private final String name;
    private final String id;
    private final Set<HypercubePlayer> players;

    public HypercubeNode(String name) {
        this.name = name;
        id = name.toLowerCase().replace(" ", "_");
        players = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Set<HypercubePlayer> getPlayers() {
        return players;
    }

    public void addPlayer(HypercubePlayer player) {
        players.add(player);
    }

    public void clearPlayers() {
        players.clear();
    }

    public void removePlayer(UUID uuid) {
        players.removeIf(p -> p.uuid().equals(uuid));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HypercubeNode that = (HypercubeNode) o;
        return Objects.equals(id, that.id);
    }

}
