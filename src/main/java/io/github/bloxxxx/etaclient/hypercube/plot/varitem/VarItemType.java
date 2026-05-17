package io.github.bloxxxx.etaclient.hypercube.plot.varitem;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl.*;

public enum VarItemType {

    NUMBER("num", NumberVarItem::fromJson),
    STRING("txt", StringVarItem::fromJson),
    TEXT("comp", TextVarItem::fromJson),
    LOCATION("loc", LocationVarItem::fromJson),
    VECTOR("vec", VectorVarItem::fromJson),
    SOUND("snd", SoundVarItem::fromJson),
    PARTICLE("part", ParticleVarItem::fromJson),
    POTION("pot", PotionVarItem::fromJson),
    VARIABLE("var", VariableVarItem::fromJson),
    GAME_VALUE("g_val", GameValueVarItem::fromJson),
    PARAMETER("pn_el", ParameterVarItem::fromJson),
    ITEM("item", ItemVarItem::fromJson),
    TAG("bl_tag", TagVarItem::fromJson);











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
