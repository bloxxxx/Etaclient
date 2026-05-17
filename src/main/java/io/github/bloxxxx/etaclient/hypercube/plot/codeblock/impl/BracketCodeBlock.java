package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record BracketCodeBlock(Direction direction, Type type) implements CodeBlock {

    public static BracketCodeBlock fromJson(JsonObject data) {
        return new BracketCodeBlock(
                Direction.fromString(JsonUtil.getAsStringDef(data, "direct", "")),
                Type.fromString(JsonUtil.getAsStringDef(data, "type", ""))
        );
    }

    public enum Direction {
        OPEN,
        CLOSE;

        public static Direction fromString(String string) {
            return switch (string) {
                case "open" -> OPEN;
                case "close" -> CLOSE;
                default -> null;
            };
        }
    }

    public enum Type {
        NORMAL,
        REPEAT;

        public static Type fromString(String string) {
            return switch (string) {
                case "norm" -> NORMAL;
                case "repeat" -> REPEAT;
                default -> null;
            };
        }
    }
}
