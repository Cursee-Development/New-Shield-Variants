package com.cursee.new_shield_variants.core.common.registry;

import com.cursee.new_shield_variants.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryForge {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static void register(IEventBus modEventBus) {

        ModItemsForge.register();
        ModTabForge.register();

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TAB.register(modEventBus);
    }

    public static <T extends Item> RegistryObject<T > registerItem(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }

    public static <T extends CreativeModeTab> RegistryObject<T > registerTab(String id, Supplier<T> tab) {
        return CREATIVE_MODE_TAB.register(id, tab);
    }
}
