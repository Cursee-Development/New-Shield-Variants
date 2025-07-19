package com.cursee.new_shield_variants.core.registry;

import com.cursee.new_shield_variants.NewShieldVariants;
import com.cursee.new_shield_variants.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public class ModTabs {

    public static final CreativeModeTab NSV_TAB = Services.PLATFORM.tabBuilder()
            .icon(() -> new ItemStack(ModItems.DRAGON_HEAD_SHIELD))
            .title(Component.translatable("itemGroup.newShieldVariants"))
            .displayItems(ModItems::addShields)
            .build();

    public static void register(BiConsumer<CreativeModeTab, ResourceLocation> consumer) {
        consumer.accept(NSV_TAB, NewShieldVariants.identifier("nsv_tab"));
    }
}
