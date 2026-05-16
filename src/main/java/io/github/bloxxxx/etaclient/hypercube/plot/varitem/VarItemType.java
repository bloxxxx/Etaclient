package io.github.bloxxxx.etaclient.hypercube.plot.varitem;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl.*;

public enum VarItemType {

    NUMBER("num", NumberVarItem::new),
    STRING("txt", StringVarItem::new),
    TEXT("comp", TextVarItem::new),
    LOCATION("loc", LocationVarItem::new),
    VECTOR("vec", VectorVarItem::new),
    SOUND("snd", SoundVarItem::new),
    // TODO: PARTICLE
    POTION("pot", PotionVarItem::new),
    VARIABLE("var", VariableVarItem::new),
    GAME_VALUE("g_val", GameValueVarItem::new);
    // TODO: PARAMETER












    public final String id;
    private final VarItemConstructor constructor;
    VarItemType(String id, VarItemConstructor constructor) {
        this.id = id;
        this.constructor = constructor;
    }

    public VarItem construct(JsonObject data) {
        try {
            return constructor.construct(data);
        } catch (NullPointerException | IllegalStateException | UnsupportedOperationException e) {
            throw new VarItemConstructingException(e);
        }
    }

    public static VarItemType fromId(String id) {
        for (VarItemType type : values()) {
            if (type.id.equals(id)) return type;
        }
        return null;
    }
}
