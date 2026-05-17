package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record CallFunctionCodeBlock(String name, CodeBlockArguments arguments) implements CodeBlock {

    public static CallFunctionCodeBlock fromJson(JsonObject data) {
        return new CallFunctionCodeBlock(
                JsonUtil.getAsStringDef(data, "data", ""),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
