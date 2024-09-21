package com.cursee.new_shield_variants.core.common.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class ModTabForge {

    public static void register() {}

    public static final RegistryObject<CreativeModeTab> NEWSHIELDVARIANTS_TAB = RegistryForge.registerTab("newshieldvariants_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItemsForge.STONE_SHIELD.get()))
                    .title(Component.translatable("itemGroup.newShieldVariants"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItemsForge.STONE_SHIELD.get());
                        output.accept(ModItemsForge.IRON_SHIELD.get());
                        output.accept(ModItemsForge.GOLD_SHIELD.get());
                        output.accept(ModItemsForge.DIAMOND_SHIELD.get());
                        output.accept(ModItemsForge.NETHERITE_SHIELD.get());

                        output.accept(ModItemsForge.ENDER_SHIELD.get());
                        output.accept(ModItemsForge.BLAZE_SHIELD.get());
                        output.accept(ModItemsForge.FIRE_CHARGE_SHIELD.get());

                        output.accept(ModItemsForge.SHULKER_SHIELD.get());
                        output.accept(ModItemsForge.DRAGON_HEAD_SHIELD.get());
                        output.accept(ModItemsForge.TNT_SHIELD.get());

                        // output.accept(ModItemsForge.REDSTONE_SHIELD.get());
                    }).build());
}
