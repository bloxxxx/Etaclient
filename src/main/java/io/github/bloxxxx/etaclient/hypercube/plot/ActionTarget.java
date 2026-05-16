package io.github.bloxxxx.etaclient.hypercube.plot;

public enum ActionTarget {
    DEFAULT("Default"),
    SELECTION("Selection"),
    KILLER("Killer"),
    DAMAGER("Damager"),
    VICTIM("Victim"),
    SHOOTER("Shooter"),
    PROJECTILE("Projectile"),
    LAST_ENTITY("LastEntity");

    public final String id;
    ActionTarget(String id) {
        this.id = id;
    }

    public static ActionTarget fromId(String id) {
        for (ActionTarget target : values()) {
            if (target.id.equals(id)) return target;
        }
        return null;
    }
}
