package io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeBlock;

public record ElseCodeBlock() implements CodeBlock {
    public static ElseCodeBlock fromJson(JsonObject ignored) {
        return new ElseCodeBlock();
    }
}
