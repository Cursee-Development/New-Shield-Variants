package com.cursee.new_shield_variants.core.world.item;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ShieldVariant {

    STONE(Ingredient.of(ItemTags.STONE_CRAFTING_MATERIALS)),
    IRON(Ingredient.of(Items.IRON_INGOT)),
    GOLD(Ingredient.of(Items.GOLD_INGOT)),
    DIAMOND(Ingredient.of(Items.DIAMOND)),
    NETHERITE(Ingredient.of(Items.NETHERITE_INGOT)),

    ENDER(Ingredient.of(Items.ENDER_PEARL)),
    BLAZE(Ingredient.of(Items.BLAZE_ROD)),
    FIRE_CHARGE(Ingredient.of(Items.FIRE_CHARGE)),
    SHULKER(Ingredient.of(Items.SHULKER_SHELL)),
    DRAGON_HEAD(Ingredient.of(Items.DRAGON_HEAD)),
    TNT(Ingredient.of(Items.TNT)),
    REDSTONE(Ingredient.of(Items.REDSTONE));

    private final Ingredient repairIngredient;

    ShieldVariant(Ingredient repairIngredient) {
        this.repairIngredient = repairIngredient;
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
