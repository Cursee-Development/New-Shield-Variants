package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.registry.ModRegistryForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class NewShieldVariantsForge {

    public static IEventBus EVENT_BUS;

    public NewShieldVariantsForge(FMLJavaModLoadingContext context) {
        NewShieldVariants.init();
        EVENT_BUS = context.getModEventBus();
        ModRegistryForge.register(EVENT_BUS);
        if (FMLEnvironment.dist == Dist.CLIENT) new NewShieldVariantsClientForge(EVENT_BUS);
    }

    @SuppressWarnings("removal")
    public NewShieldVariantsForge() {
        this(FMLJavaModLoadingContext.get());
    }
}