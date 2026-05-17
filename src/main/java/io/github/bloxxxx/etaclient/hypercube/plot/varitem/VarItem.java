package io.github.bloxxxx.etaclient.hypercube.plot.varitem;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import io.github.bloxxxx.etaclient.util.PBVUtil;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface VarItem {
    static @Nullable VarItem parse(JsonObject root) {
        if (root == null) return null;

        String id = JsonUtil.getAsString(root, "id");
        if (id == null) return null;

        VarItemType type = VarItemType.fromId(id);
        if (type == null) return null;

        JsonObject data = JsonUtil.getAsJsonObject(root, "data");
        if (data == null) return null;

        return type.construct(data);
    }
    static VarItem parse(ItemStack stack) {
        return parse(PBVUtil.getVarItem(stack));
    }
}
