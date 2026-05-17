package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.ActionTarget;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockArguments;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlockAttribute;
import io.github.bloxxxx.etaclient.util.JsonUtil;

public record IfPlayerCodeBlock(String action, ActionTarget target, CodeBlockAttribute attribute, CodeBlockArguments arguments) implements CodeBlock {
    public static IfPlayerCodeBlock fromJson(JsonObject data) {
        return new IfPlayerCodeBlock(
                JsonUtil.getAsStringDef(data, "action", ""),
                ActionTarget.fromJson(data),
                CodeBlockAttribute.fromJson(data),
                CodeBlockArguments.fromArgsJson(data)
        );
    }
}
