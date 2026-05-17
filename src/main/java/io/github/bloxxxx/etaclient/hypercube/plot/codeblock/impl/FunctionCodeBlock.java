package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record FunctionCodeBlock(String name, CodeBlockArguments arguments) implements CodeBlock {

    public static FunctionCodeBlock fromJson(JsonObject data) {
        return new FunctionCodeBlock(
                JsonUtil.getAsStringDef(data, "data", ""),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
