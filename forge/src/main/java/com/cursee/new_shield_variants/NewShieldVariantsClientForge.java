package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.world.entity.projectile.ThrownFireChargeFabric;
import com.cursee.new_shield_variants.core.world.entity.projectile.ThrownTNTFabric;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;

public class NewShieldVariantsClientForge {

    public NewShieldVariantsClientForge(final IEventBus modEventBus) {

        modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> {
            event.enqueueWork(() -> {
                NewShieldVariantsClient.init();
            });
        });

        modEventBus.addListener((Consumer<EntityRenderersEvent.RegisterLayerDefinitions>) event -> {
            event.registerLayerDefinition(NewShieldVariantsClient.TNT_LAYER, ThrownTNTFabric::createBodyLayer);
            event.registerLayerDefinition(NewShieldVariantsClient.FIRE_CHARGE_LAYER, ThrownFireChargeFabric::createBodyLayer);
        });
    }
}
