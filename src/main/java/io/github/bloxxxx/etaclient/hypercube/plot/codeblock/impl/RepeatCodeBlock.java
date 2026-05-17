package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockAttribute;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record RepeatCodeBlock(String action, String subAction, CodeBlockAttribute attribute, CodeBlockArguments arguments) implements CodeBlock {
    public static RepeatCodeBlock fromJson(JsonObject data) {
        return new RepeatCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                JsonUtil.getAsStringDef(data, "subAction", ""),
                CodeBlockAttribute.fromJson(data),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
