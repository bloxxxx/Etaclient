package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record GameActionCodeBlock(String action, CodeBlockArguments arguments) implements CodeBlock {
    public static GameActionCodeBlock fromJson(JsonObject data) {
        return new GameActionCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
