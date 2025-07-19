package com.cursee.new_shield_variants.core.registry;

import com.cursee.new_shield_variants.NewShieldVariants;
import com.cursee.new_shield_variants.core.world.item.NSVShieldItem;
import com.cursee.new_shield_variants.core.world.item.ShieldVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class ModItems {

    public static final Item STONE_SHIELD = new NSVShieldItem(ShieldVariant.STONE, new Item.Properties().durability(500));
    public static final Item IRON_SHIELD = new NSVShieldItem(ShieldVariant.IRON, new Item.Properties().durability(1000));
    public static final Item GOLD_SHIELD = new NSVShieldItem(ShieldVariant.GOLD, new Item.Properties().durability(750));
    public static final Item DIAMOND_SHIELD = new NSVShieldItem(ShieldVariant.DIAMOND, new Item.Properties().durability(1750));
    public static final Item NETHERITE_SHIELD = new NSVShieldItem(ShieldVariant.NETHERITE, new Item.Properties().durability(2000));

    public static final Item ENDER_SHIELD = new NSVShieldItem(ShieldVariant.ENDER, new Item.Properties().durability(2250));
    public static final Item BLAZE_SHIELD = new NSVShieldItem(ShieldVariant.BLAZE, new Item.Properties().durability(2250));
    public static final Item FIRE_CHARGE_SHIELD = new NSVShieldItem(ShieldVariant.FIRE_CHARGE, new Item.Properties().durability(2250));
    public static final Item SHULKER_SHIELD = new NSVShieldItem(ShieldVariant.SHULKER, new Item.Properties().durability(2250));
    public static final Item DRAGON_HEAD_SHIELD = new NSVShieldItem(ShieldVariant.DRAGON_HEAD, new Item.Properties().durability(2250));
    public static final Item TNT_SHIELD = new NSVShieldItem(ShieldVariant.TNT, new Item.Properties().durability(2250));
    public static final Item REDSTONE_SHIELD = new NSVShieldItem(ShieldVariant.REDSTONE, new Item.Properties().durability(2250));

    public static void register(BiConsumer<Item, ResourceLocation> consumer) {
        consumer.accept(STONE_SHIELD, NewShieldVariants.identifier("stone_shield"));
        consumer.accept(IRON_SHIELD, NewShieldVariants.identifier("iron_shield"));
        consumer.accept(GOLD_SHIELD, NewShieldVariants.identifier("gold_shield"));
        consumer.accept(DIAMOND_SHIELD, NewShieldVariants.identifier("diamond_shield"));
        consumer.accept(NETHERITE_SHIELD, NewShieldVariants.identifier("netherite_shield"));
        consumer.accept(ENDER_SHIELD, NewShieldVariants.identifier("ender_shield"));
        consumer.accept(BLAZE_SHIELD, NewShieldVariants.identifier("blaze_shield"));
        consumer.accept(FIRE_CHARGE_SHIELD, NewShieldVariants.identifier("fire_charge_shield"));
        consumer.accept(SHULKER_SHIELD, NewShieldVariants.identifier("shulker_shield"));
        consumer.accept(DRAGON_HEAD_SHIELD, NewShieldVariants.identifier("dragon_head_shield"));
        consumer.accept(TNT_SHIELD, NewShieldVariants.identifier("tnt_shield"));
        consumer.accept(REDSTONE_SHIELD, NewShieldVariants.identifier("redstone_shield"));
    }

    public static void addShields(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {

        output.accept(STONE_SHIELD);
        output.accept(IRON_SHIELD);
        output.accept(GOLD_SHIELD);
        output.accept(DIAMOND_SHIELD);
        output.accept(NETHERITE_SHIELD);

        output.accept(ENDER_SHIELD);
        output.accept(BLAZE_SHIELD);
        output.accept(FIRE_CHARGE_SHIELD);
        output.accept(SHULKER_SHIELD);
        output.accept(DRAGON_HEAD_SHIELD);
        output.accept(TNT_SHIELD);
    }
}
