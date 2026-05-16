package io.github.bloxxxx.etaclient.hypercube.plot;

public enum VariableScope {
    GAME("unsaved"),
    SAVED("saved"),
    LOCAL("local"),
    LINE("line");

    public final String id;
    VariableScope(String id) {
        this.id = id;
    }

    public static VariableScope fromId(String id) {
        for (VariableScope scope : values()) {
            if (scope.id.equals(id)) return scope;
        }
        return null;
    }
}
