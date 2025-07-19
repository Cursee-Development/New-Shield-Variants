package com.cursee.new_shield_variants.core.registry;

import com.cursee.new_shield_variants.core.registry.ModBlocks;
import com.cursee.new_shield_variants.core.registry.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ModRegistryFabric {

    public static void register() {
        ModBlocks.register(bind(BuiltInRegistries.BLOCK));
        ModItems.register(bind(BuiltInRegistries.ITEM));
        ModTabs.register(bind(BuiltInRegistries.CREATIVE_MODE_TAB));
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }
}
