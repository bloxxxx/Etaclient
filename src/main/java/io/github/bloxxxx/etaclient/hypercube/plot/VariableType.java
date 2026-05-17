package io.github.bloxxxx.etaclient.hypercube.plot;

public enum VariableType {

    NUMBER("num"),
    STRING("txt"),
    TEXT("comp"),
    LOCATION("loc"),
    VECTOR("vec"),
    SOUND("snd"),
    PARTICLE("part"),
    POTION("pot"),
    VARIABLE("var"),

    ITEM("item"),

    ANY("any"),
    LIST("list"),
    DICTIONARY("dict");


    public final String id;
    VariableType(String id) {
        this.id = id;
    }

    public static VariableType fromId(String id) {
        for (VariableType type : values()) {
            if (type.id.equals(id)) return type;
        }
        return null;
    }
}
