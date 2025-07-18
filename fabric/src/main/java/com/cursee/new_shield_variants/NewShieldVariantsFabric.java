package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.registry.ModRegistryFabric;
import net.fabricmc.api.ModInitializer;

public class NewShieldVariantsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        NewShieldVariants.init();
        ModRegistryFabric.register();
    }
}
