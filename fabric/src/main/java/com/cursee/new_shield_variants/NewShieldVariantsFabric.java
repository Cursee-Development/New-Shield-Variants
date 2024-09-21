package com.cursee.new_shield_variants;

import com.cursee.monolib.core.MonoLibConfiguration;
import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.new_shield_variants.core.common.registry.ModItemsFabric;
import com.cursee.new_shield_variants.core.common.registry.RegistryFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class NewShieldVariantsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        NewShieldVariants.init();

        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);

        NewShieldVariants.debugCommon = MonoLibConfiguration.debugging;

        RegistryFabric.register();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(ModItemsFabric::addShields);

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
    }

    public static class PlayerTickHandler implements ServerTickEvents.StartTick {

        @Override
        public void onStartTick(MinecraftServer server) {

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {

                if (!player.isBlocking()) return;

                // BLAZE_SHIELD
                if (player.getMainHandItem().is(ModItemsFabric.BLAZE_SHIELD) || player.getOffhandItem().is(ModItemsFabric.BLAZE_SHIELD)) {

                    List<LivingEntity> nearbyLivingEntities = player.serverLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat(), player, player.getBoundingBox().inflate(3));

                    if (!nearbyLivingEntities.isEmpty()) nearbyLivingEntities.forEach(livingEntity -> livingEntity.setRemainingFireTicks(4 * 20));

                    return;
                }

                // ENDER_SHIELD
                if (player.getMainHandItem().is(ModItemsFabric.ENDER_SHIELD) || player.getOffhandItem().is(ModItemsFabric.ENDER_SHIELD)) {

                    LivingEntity lastAttacker = player.getLastAttacker();
                    List<LivingEntity> nearbyLivingEntities = player.serverLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat(), player, player.getBoundingBox().inflate(3));
                    LivingEntity targetForTeleportation = lastAttacker == null && !nearbyLivingEntities.isEmpty() ? nearbyLivingEntities.get(0) : lastAttacker;

                    if (targetForTeleportation != null && randomTeleport(targetForTeleportation)) {
                        player.stopUsingItem();
                        player.setLastHurtByMob(null);
                    }

                    return;
                }

                // SHULKER_SHIELD
                if (player.getMainHandItem().is(ModItemsFabric.SHULKER_SHIELD) || player.getOffhandItem().is(ModItemsFabric.SHULKER_SHIELD)) {

                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (player.getRandom().nextDouble() * 40)));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (player.getRandom().nextDouble() * 160)));

                    return;
                }
            }
        }
    }

    public static boolean randomTeleport(LivingEntity livingEntity) {

        Level $$1 = livingEntity.level();

        double $$5 = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
        double $$6 = Mth.clamp(livingEntity.getY() + (double)(livingEntity.getRandom().nextInt(16) - 8), (double)$$1.getMinBuildHeight(), (double)($$1.getMinBuildHeight() + ((ServerLevel)$$1).getLogicalHeight() - 1));
        double $$7 = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
        if (livingEntity.isPassenger()) {
            livingEntity.stopRiding();
        }

        Vec3 $$8 = livingEntity.position();
        if (livingEntity.randomTeleport($$5, $$6, $$7, true)) {

            $$1.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of(livingEntity));
            SoundSource $$12 = SoundSource.NEUTRAL;
            SoundEvent $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;

            $$1.playSound((Player)null, BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()), $$11, $$12);
            livingEntity.resetFallDistance();
            return true;
        }
        else {

            $$1.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of(livingEntity));
            SoundSource $$12 = SoundSource.NEUTRAL;
            SoundEvent $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;

            $$1.playSound((Player)null, BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()), $$11, $$12);
            livingEntity.resetFallDistance();
            return false;
        }

    }
}
