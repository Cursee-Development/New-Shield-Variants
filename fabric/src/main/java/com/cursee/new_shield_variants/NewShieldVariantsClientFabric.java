package com.cursee.new_shield_variants;

import net.fabricmc.api.ClientModInitializer;

public class NewShieldVariantsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NewShieldVariantsClient.init();
    }
}
