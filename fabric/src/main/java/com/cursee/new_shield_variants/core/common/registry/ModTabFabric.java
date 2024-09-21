package com.cursee.new_shield_variants.core.common.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabFabric {

    public static void register() {}

    public static final CreativeModeTab NEW_SHIELD_VARIANTS_TAB = RegistryFabric.registerTab("new_shield_variants_tab", FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.newShieldVariants"))
            .icon(() -> new ItemStack(ModItemsFabric.DRAGON_HEAD_SHIELD))
            .displayItems(((itemDisplayParameters, output) -> {
                ModItemsFabric.addShields(output);
            })).build()
    );
}
