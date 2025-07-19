package com.cursee.new_shield_variants;

import com.cursee.new_shield_variants.core.registry.ModRegistryForge;
import com.cursee.new_shield_variants.core.world.item.NSVTickInteractions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class NewShieldVariantsForge {

    public static IEventBus EVENT_BUS;

    public NewShieldVariantsForge(FMLJavaModLoadingContext context) {
        NewShieldVariants.init();
        EVENT_BUS = context.getModEventBus();
        ModRegistryForge.register(EVENT_BUS);
        if (FMLEnvironment.dist == Dist.CLIENT) new NewShieldVariantsClientForge(EVENT_BUS);

        MinecraftForge.EVENT_BUS.addListener(NewShieldVariantsForge::onPlayerTick);
    }

    private static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!(event.player instanceof ServerPlayer player)) return;
        if (event.phase != TickEvent.Phase.END && !player.isBlocking()) return;
        NSVTickInteractions.handle(player);
    }

    public NewShieldVariantsForge() {
        this(FMLJavaModLoadingContext.get());
    }
}