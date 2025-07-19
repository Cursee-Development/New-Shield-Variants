package com.cursee.new_shield_variants;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.new_shield_variants.core.NSVConfig;
import net.minecraft.resources.ResourceLocation;

public class NewShieldVariants {

    public static void init() {
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        NSVConfig.onLoad();
    }

    public static ResourceLocation identifier(String path) {
        return new ResourceLocation(Constants.MOD_ID, path);
    }
}