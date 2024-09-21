package com.cursee.new_shield_variants.core.client;

import com.cursee.new_shield_variants.core.common.registry.ModItemsForge;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {

    public static void registerProperties(Item item){
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, level, entity, p_174578_) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }

    public static void addCustomItemProperties() {
        registerProperties(ModItemsForge.STONE_SHIELD.get());
        registerProperties(ModItemsForge.IRON_SHIELD.get());
        registerProperties(ModItemsForge.GOLD_SHIELD.get());
        registerProperties(ModItemsForge.DIAMOND_SHIELD.get());
        registerProperties(ModItemsForge.NETHERITE_SHIELD.get());

        registerProperties(ModItemsForge.ENDER_SHIELD.get());
        registerProperties(ModItemsForge.BLAZE_SHIELD.get());
        registerProperties(ModItemsForge.FIRE_CHARGE_SHIELD.get());

        registerProperties(ModItemsForge.SHULKER_SHIELD.get());
        registerProperties(ModItemsForge.DRAGON_HEAD_SHIELD.get());
        registerProperties(ModItemsForge.TNT_SHIELD.get());

        registerProperties(ModItemsForge.REDSTONE_SHIELD.get());
    }


}
