package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockAttribute;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record EntityEventCodeBlock(String name, CodeBlockAttribute attribute) implements CodeBlock {
    public static EntityEventCodeBlock fromJson(JsonObject data) {
        return new EntityEventCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                CodeBlockAttribute.fromJson(data)
        );
    }
}
