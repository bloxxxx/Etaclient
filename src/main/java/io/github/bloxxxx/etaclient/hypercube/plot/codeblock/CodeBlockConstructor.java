package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonObject;

public interface CodeBlockConstructor {
    CodeBlock construct(JsonObject data);
}
