package com.cursee.new_shield_variants.core.common.registry;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RegistryFabric {

    public static void register() {

        ModItemsFabric.register();
    }

    public static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, id), item);
    }
}
