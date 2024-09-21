package com.cursee.new_shield_variants.core.common.registry;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class RegistryFabric {

    public static void register() {

        ModItemsFabric.register();
        ModTabFabric.register();
    }

    public static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, id), item);
    }

    public static CreativeModeTab registerTab(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, id), tab);
    }
}
