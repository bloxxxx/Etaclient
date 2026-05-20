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

    public boolean is(PresetNode presetNode) {
        return id.equals(presetNode.id);
    }

    public enum PresetNode {
        NODE_1("node_1"),
        NODE_2("node_2"),
        NODE_3("node_3"),
        NODE_4("node_4"),
        NODE_5("node_5"),
        NODE_6("node_6"),
        NODE_7("node_7"),
        NODE_BETA("node_beta");

        public final String id;
        PresetNode(String id) {
            this.id = id;
        }
    }

}
