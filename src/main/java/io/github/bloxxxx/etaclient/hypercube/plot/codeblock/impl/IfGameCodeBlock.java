package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockAttribute;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record IfGameCodeBlock(String action, CodeBlockAttribute attribute, CodeBlockArguments arguments) implements CodeBlock {
    public static IfGameCodeBlock fromJson(JsonObject data) {
        return new IfGameCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                CodeBlockAttribute.fromJson(data),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
