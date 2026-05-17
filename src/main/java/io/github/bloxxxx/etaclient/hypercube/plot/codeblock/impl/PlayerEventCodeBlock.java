package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockAttribute;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record PlayerEventCodeBlock(String name, CodeBlockAttribute attribute) implements CodeBlock {
    public static PlayerEventCodeBlock fromJson(JsonObject data) {
        return new PlayerEventCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                CodeBlockAttribute.fromJson(data)
        );
    }
}
