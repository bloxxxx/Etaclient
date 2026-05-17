package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.ActionTarget;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record EntityActionCodeBlock(String action, ActionTarget target, CodeBlockArguments arguments) implements CodeBlock {
    public static EntityActionCodeBlock fromJson(JsonObject data) {
        return new EntityActionCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                ActionTarget.fromJson(data),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
