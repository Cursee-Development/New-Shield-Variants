package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.registry.ModRegistryFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class NewShieldVariantsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        NewShieldVariants.init();
        ModRegistryFabric.register();
    }
}
