package io.github.bloxxxx.etaclient.hypercube.plot;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.util.JsonUtil;

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
        return DEFAULT;
    }

    public static ActionTarget fromJson(JsonObject root) {
        return fromId(JsonUtil.getAsStringDef(root, "target", ""));
    }
}
