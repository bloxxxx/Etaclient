package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockAttribute;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record GameEventCodeBlock(String name, CodeBlockAttribute attribute) implements CodeBlock {
    public static GameEventCodeBlock fromJson(JsonObject data) {
        return new GameEventCodeBlock(
                JsonUtil.getAsStringDef(data, "name", ""),
                CodeBlockAttribute.fromJson(data)
        );
    }
}
