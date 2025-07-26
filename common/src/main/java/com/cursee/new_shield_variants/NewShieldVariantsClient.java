package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.registry.ModEntities;
import com.cursee.new_shield_variants.platform.Services;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;

public class NewShieldVariantsClient {

    public static final ModelLayerLocation TNT_LAYER = new ModelLayerLocation(
            NewShieldVariants.identifier("tnt_layer"), "main");

    public static final ModelLayerLocation FIRE_CHARGE_LAYER = new ModelLayerLocation(
            NewShieldVariants.identifier("fire_charge_layer"), "main");

    public static void init() {
        Services.PLATFORM.registerEntityRenderer(ModEntities.THROWN_TNT, ThrownItemRenderer::new);
        Services.PLATFORM.registerEntityRenderer(ModEntities.THROWN_FIRE_CHARGE, ThrownItemRenderer::new);
    }
}
