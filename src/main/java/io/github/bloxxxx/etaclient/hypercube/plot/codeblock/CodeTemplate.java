package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.bloxxxx.etaclient.util.GzipUtil;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CodeTemplate {

    public final List<CodeBlock> blocks;
    public final Data data;
    public CodeTemplate(Data data) {
        blocks = new ArrayList<>();
        this.data = data;
    }

    public static @Nullable CodeTemplate fromJson(JsonObject root) {
        if (root == null) return null;

        CodeTemplate res = new CodeTemplate(Data.fromJson(root));
        String code = JsonUtil.getAsString(root, "code");
        if (code == null) return null;
        String decompressed = GzipUtil.decompress(code);
        if (decompressed == null) return null;
        JsonElement element = JsonParser.parseString(decompressed);
        if (element == null) return null;
        if (!element.isJsonObject()) return null;
        JsonObject codeRoot = element.getAsJsonObject();

        JsonArray blocks = JsonUtil.getAsJsonArray(codeRoot, "blocks");
        if (blocks == null) return null;

        for (JsonElement blockEl : blocks) {
            if (!blockEl.isJsonObject()) continue;
            JsonObject block = blockEl.getAsJsonObject();

            CodeBlock codeBlock = CodeBlock.parse(block);
            if (codeBlock != null) res.blocks.add(codeBlock);
        }

        return res;
    }

    @Override
    public String toString() {
        return "CodeTemplate{" +
                "blocks=" + blocks +
                ", data=" + data +
                '}';
    }

    public record Data(String author, String name) {
        public static Data fromJson(JsonObject root) {
            return new Data(
                    JsonUtil.getAsStringDef(root, "author", ""),
                    JsonUtil.getAsStringDef(root, "name", "")
            );
        }
    }
}
