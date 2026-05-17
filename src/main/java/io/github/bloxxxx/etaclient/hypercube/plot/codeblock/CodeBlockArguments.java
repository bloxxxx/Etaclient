package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class CodeBlockArguments {

    public final Map<Integer, VarItem> slots;
    public CodeBlockArguments() {
        slots = new HashMap<>();
    }

    public static CodeBlockArguments fromJson(JsonObject root) {
        JsonArray array = JsonUtil.getAsJsonArrayDefEmpty(root, "items");

        CodeBlockArguments res = new CodeBlockArguments();

        for (JsonElement el : array) {
            if (!el.isJsonObject()) continue;
            JsonObject arg = el.getAsJsonObject();

            res.slots.put(
                    JsonUtil.getAsIntDef(arg, "slot", 0),
                    VarItem.parse(JsonUtil.getAsJsonObjectDefEmpty(arg, "item"))
            );
        }

        return res;
    }

    public static CodeBlockArguments fromArgsJson(JsonObject root) {
        return fromJson(JsonUtil.getAsJsonObjectDefEmpty(root, "args"));
    }
}
