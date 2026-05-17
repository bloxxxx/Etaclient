package io.github.bloxxxx.etaclient.hypercube.plot.varitem;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.bloxxxx.etaclient.util.PBVUtil;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
//TODO: Remake using JsonUtil
public interface VarItem {
    static @Nullable VarItem parse(JsonObject root) {
        if (root == null) return null;

        JsonElement idElement = root.get("id");
        if (idElement == null) return null;
        if (!idElement.isJsonPrimitive()) return null;
        JsonPrimitive idPrimitive = idElement.getAsJsonPrimitive();
        if (!idPrimitive.isString()) return null;
        String id = idElement.getAsString();

        VarItemType type = VarItemType.fromId(id);
        if (type == null) return null;

        JsonElement dataElement = root.get("data");
        if (dataElement == null) return null;
        if (!dataElement.isJsonObject()) return null;
        JsonObject data = dataElement.getAsJsonObject();

        return type.construct(data);
    }
    static VarItem parse(ItemStack stack) {
        return parse(PBVUtil.getVarItem(stack));
    }
}
