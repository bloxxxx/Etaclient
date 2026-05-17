package io.github.bloxxxx.etaclient.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.CodeTemplate;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.jetbrains.annotations.Nullable;

public final class PBVUtil {
    private PBVUtil() {}

    private static final String PBV_KEY = "PublicBukkitValues";
    private static final String HYPERCUBE_NAMESPACE = "hypercube:";
    private static final boolean DEFAULT_HYPERCUBE = true;

    private static final String ITEM_INSTANCE = "item_instance";
    private static final String VAR_ITEM = "varitem";
    private static final String CODE_TEMPLATE_DATA = "codetemplatedata";

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

    public static JsonObject getCodeTemplateData(ItemStack itemStack) {
        return getJsonKey(itemStack, CODE_TEMPLATE_DATA);
    }

    public static @Nullable CodeTemplate getCodeTemplate(ItemStack itemStack) {
        JsonObject codeTemplateData = getCodeTemplateData(itemStack);
        if (codeTemplateData == null) return null;
        return CodeTemplate.fromJson(getCodeTemplateData(itemStack));
    }

}
