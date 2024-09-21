package com.cursee.new_shield_variants.core.common.registry;

import com.cursee.new_shield_variants.core.common.item.custom.DragonHeadShieldItem;
import com.cursee.new_shield_variants.core.common.item.custom.FireChargeShieldItem;
import com.cursee.new_shield_variants.core.common.item.custom.TNTShieldItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsForge {

    public static void register() {}

    public static final RegistryObject<Item> STONE_SHIELD = RegistryForge.registerItem("stone_shield", () -> new ShieldItem(new Item.Properties().durability(500)));
    public static final RegistryObject<Item> IRON_SHIELD = RegistryForge.registerItem("iron_shield", () -> new ShieldItem(new Item.Properties().durability(1000)));
    public static final RegistryObject<Item> GOLD_SHIELD = RegistryForge.registerItem("gold_shield", () -> new ShieldItem(new Item.Properties().durability(750)));
    public static final RegistryObject<Item> DIAMOND_SHIELD = RegistryForge.registerItem("diamond_shield", () -> new ShieldItem(new Item.Properties().durability(1750)));
    public static final RegistryObject<Item> NETHERITE_SHIELD = RegistryForge.registerItem("netherite_shield", () -> new ShieldItem(new Item.Properties().durability(2000)));

    public static final RegistryObject<Item> ENDER_SHIELD = RegistryForge.registerItem("ender_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
    public static final RegistryObject<Item> BLAZE_SHIELD = RegistryForge.registerItem("blaze_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
    public static final RegistryObject<Item> FIRE_CHARGE_SHIELD = RegistryForge.registerItem("fire_charge_shield", () -> new FireChargeShieldItem(new Item.Properties().durability(2250)));

    public static final RegistryObject<Item> SHULKER_SHIELD = RegistryForge.registerItem("shulker_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
    public static final RegistryObject<Item> DRAGON_HEAD_SHIELD = RegistryForge.registerItem("dragon_head_shield", () -> new DragonHeadShieldItem(new Item.Properties().durability(2250)));
    public static final RegistryObject<Item> TNT_SHIELD = RegistryForge.registerItem("tnt_shield", () -> new TNTShieldItem(new Item.Properties().durability(2250)));

    public static final RegistryObject<Item> REDSTONE_SHIELD = RegistryForge.registerItem("redstone_shield", () -> new ShieldItem(new Item.Properties().durability(2250)));
}
