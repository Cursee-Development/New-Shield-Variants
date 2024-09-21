package com.cursee.new_shield_variants.core.common.registry;

import com.cursee.new_shield_variants.core.common.item.custom.DragonHeadFabricShieldItem;
import com.cursee.new_shield_variants.core.common.item.custom.FireChargeFabricShieldItem;
import com.cursee.new_shield_variants.core.common.item.custom.TNTFabricShieldItem;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;

public class ModItemsFabric {

    public static void register() {}

    public static final Item STONE_SHIELD = RegistryFabric.registerItem("stone_shield", new FabricShieldItem(new FabricItemSettings().maxDamage(500), 10, 2, Items.STONE));
    public static final Item IRON_SHIELD = RegistryFabric.registerItem("iron_shield", new FabricShieldItem(new FabricItemSettings().maxDamage(1000), 10, 6, Items.IRON_INGOT));
    public static final Item GOLD_SHIELD = RegistryFabric.registerItem("gold_shield", new FabricShieldItem(new FabricItemSettings().maxDamage(750), 10, 4, Items.GOLD_INGOT));
    public static final Item DIAMOND_SHIELD = RegistryFabric.registerItem("diamond_shield", new FabricShieldItem(new FabricItemSettings().maxDamage(1750), 10, 8, Items.DIAMOND));
    public static final Item NETHERITE_SHIELD = RegistryFabric.registerItem("netherite_shield", new FabricShieldItem(new FabricItemSettings().maxDamage(2000), 10, 16, Items.NETHERITE_INGOT));

    public static final Item ENDER_SHIELD = RegistryFabric.registerItem("ender_shield",  new FabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.ENDER_PEARL));
    public static final Item BLAZE_SHIELD = RegistryFabric.registerItem("blaze_shield",  new FabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.BLAZE_ROD));
    public static final Item FIRE_CHARGE_SHIELD = RegistryFabric.registerItem("fire_charge_shield",  new FireChargeFabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.REDSTONE));
    public static final Item SHULKER_SHIELD = RegistryFabric.registerItem("shulker_shield",  new FabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.SHULKER_SHELL));
    public static final Item DRAGON_HEAD_SHIELD = RegistryFabric.registerItem("dragon_head_shield",  new DragonHeadFabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.REDSTONE));
    public static final Item TNT_SHIELD = RegistryFabric.registerItem("tnt_shield",  new TNTFabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.REDSTONE));

    public static final Item REDSTONE_SHIELD = RegistryFabric.registerItem("redstone_shield",  new FabricShieldItem(new FabricItemSettings().maxDamage(2250), 10, 12, Items.REDSTONE));

    public static void addShields(CreativeModeTab.Output output) {

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
