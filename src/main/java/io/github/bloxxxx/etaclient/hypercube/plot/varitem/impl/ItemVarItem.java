package io.github.bloxxxx.etaclient.hypercube.plot.varitem.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.util.JsonUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public record ItemVarItem(String id, int count, int dfNbt, JsonObject components) implements VarItem {
    public static ItemVarItem fromJson(JsonObject data) {
        JsonElement element = JsonParser.parseString(JsonUtil.getAsStringDef(data, "item", "{}"));
        JsonObject item = new JsonObject();
        if (element != null && element.isJsonObject()) item = element.getAsJsonObject();

        return new ItemVarItem(
                JsonUtil.getAsStringDef(item, "id", ""),
                JsonUtil.getAsIntDef(item, "count", 0),
                JsonUtil.getAsIntDef(item, "DF_NBT", 0),
                JsonUtil.getAsJsonObjectDefEmpty(item, "components")
        );
    }

    public ItemStack getStack() {

        Identifier identifier = Identifier.splitOn(id, ':');
        Item item = Registries.ITEM.get(identifier);
        if (item.equals(Items.AIR)) return ItemStack.EMPTY;

        ItemStack stack = item.getDefaultStack();
        stack.setCount(count);

        DataResult<ComponentChanges> dataResult = ComponentChanges.CODEC.parse(JsonOps.INSTANCE, components);
        if (dataResult.isError()) return ItemStack.EMPTY;

        stack.applyChanges(dataResult.getOrThrow());

        return stack;
    }
}
