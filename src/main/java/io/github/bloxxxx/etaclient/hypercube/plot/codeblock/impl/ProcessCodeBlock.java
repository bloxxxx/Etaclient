package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record ProcessCodeBlock(String name, CodeBlockArguments arguments) implements CodeBlock {

    public static ProcessCodeBlock fromJson(JsonObject data) {
        return new ProcessCodeBlock(
                JsonUtil.getAsStringDef(data, "data", ""),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
