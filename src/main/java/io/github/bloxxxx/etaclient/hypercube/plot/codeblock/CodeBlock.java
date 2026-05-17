package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import org.jetbrains.annotations.Nullable;

public interface CodeBlock {
    static @Nullable CodeBlock parse(JsonObject root) {
        if (root == null) return null;

        String id = JsonUtil.getAsString(root, "id");
        if (id == null) return null;

        @Nullable String block = JsonUtil.getAsString(root, "block");

        CodeBlockType type = CodeBlockType.fromIdAndBlock(id, block);
        if (type == null) return null;

        return type.construct(root);
    }
}
