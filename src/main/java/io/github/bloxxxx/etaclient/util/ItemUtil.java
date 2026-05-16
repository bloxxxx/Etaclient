package io.github.bloxxxx.etaclient.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;

public final class ItemUtil {
    private ItemUtil() {}


    public static NbtComponent getCustomData(ItemStack itemStack) {
        return itemStack.get(DataComponentTypes.CUSTOM_DATA);
    }
}
