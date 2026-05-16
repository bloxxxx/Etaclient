package io.github.bloxxxx.etaclient.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public final class PBVUtil {
    private PBVUtil() {}

    private static String PBV_KEY = "PublicBukkitValues";
    private static String HYPERCUBE_NAMESPACE = "hypercube:";
    private static boolean DEFAULT_HYPERCUBE = true;

    private static String ITEM_INSTANCE = "item_instance";
    private static String VAR_ITEM = "varitem";

    public static NbtCompound get(ItemStack itemStack) {
        NbtComponent customData = ItemUtil.getCustomData(itemStack);
        if (customData == null) return null;
        NbtCompound compound = customData.copyNbt();
        NbtElement element = compound.get(PBV_KEY);
        if (element == null) return null;
        return element.asCompound().orElse(null);
    }

    public static NbtElement getKey(ItemStack itemStack, String key, boolean isHypercube) {
        NbtCompound compound = get(itemStack);
        if (compound == null) return null;
        return compound.get((isHypercube ? HYPERCUBE_NAMESPACE : "") + key);
    }
    public static NbtElement getKey(ItemStack itemStack, String key) {
        return getKey(itemStack, key, DEFAULT_HYPERCUBE);
    }

    public static String getStringKey(ItemStack itemStack, String key, boolean isHypercube) {
        NbtElement element = getKey(itemStack, key, isHypercube);
        if (element == null) return null;
        return element.asString().orElse(null);
    }
    public static String getStringKey(ItemStack itemStack, String key) {
        return getStringKey(itemStack, key, DEFAULT_HYPERCUBE);
    }

    public static JsonObject getJsonKey(ItemStack itemStack, String key, boolean isHypercube) {
        String string = getStringKey(itemStack, key, isHypercube);
        if (string == null) return null;
        JsonElement element = JsonParser.parseString(string);
        if (!element.isJsonObject()) return null;
        return element.getAsJsonObject();
    }
    public static JsonObject getJsonKey(ItemStack itemStack, String key) {
        return getJsonKey(itemStack, key, DEFAULT_HYPERCUBE);
    }

    public static String getItemInstance(ItemStack itemStack) {
        return getStringKey(itemStack, ITEM_INSTANCE);
    }

    public static JsonObject getVarItem(ItemStack itemStack) {
        return getJsonKey(itemStack, VAR_ITEM);
    }

}
