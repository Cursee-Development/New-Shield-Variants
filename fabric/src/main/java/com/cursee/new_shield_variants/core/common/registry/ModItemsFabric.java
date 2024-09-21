package com.cursee.new_shield_variants.core.common.registry;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemsFabric {

    public static void register() {}

    public static final Item STONE_SHIELD = RegistryFabric.registerItem("stone_shield", new FabricShieldItem(new FabricItemSettings().maxDamage(500), 10, 13, Items.STONE));
}
