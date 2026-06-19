package com.cursee.new_shield_variants.core.world.item;

import com.cursee.new_shield_variants.core.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;

public class NSVTickInteractions {

    // public static void handle(NSVShieldItem shield, ShieldVariant variant, Level level, Player player, ItemStack itemstack, InteractionHand hand) {
    public static void handle(ServerPlayer player) {
        if (!(player.getUseItem().getItem() instanceof NSVShieldItem shield)) return;
        switch (shield.variant) {
            case ENDER -> NSVTickInteractions.enderShield(player.level(), player);
            case SHULKER -> NSVTickInteractions.shulkerShield(player);
        }
    }

    private static void shulkerShield(Player player) {
        if (player.getMainHandItem().is(ModItems.SHULKER_SHIELD) || player.getOffhandItem().is(ModItems.SHULKER_SHIELD)) {
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (int) (player.getRandom().nextDouble() * 40)));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (player.getRandom().nextDouble() * 160)));
        }
    }

    private static void enderShield(Level level, Player player) {

        if (level.isClientSide()) return;
        ServerLevel serverLevel = (ServerLevel) level;

        if (player.getMainHandItem().is(ModItems.ENDER_SHIELD) || player.getOffhandItem().is(ModItems.ENDER_SHIELD)) {

            LivingEntity lastAttacker = player.getLastHurtByMob();
            List<LivingEntity> nearbyLivingEntities = serverLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat(), player, player.getBoundingBox().inflate(3));
            LivingEntity targetForTeleportation = lastAttacker == null && !nearbyLivingEntities.isEmpty() ? nearbyLivingEntities.get(0) : lastAttacker;

            if (targetForTeleportation != null && randomTeleport(targetForTeleportation)) {
                player.stopUsingItem();
                player.setLastHurtByMob(null);

                player.getCooldowns().addCooldown(player.getItemInHand(player.getUsedItemHand()).getItem(), 60);
            }
        }
    }

    public static boolean randomTeleport(LivingEntity livingEntity) {

        Level level = livingEntity.level();

        double randomXPosRange = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
        double randomYPosRange = Mth.clamp(livingEntity.getY() + (double)(livingEntity.getRandom().nextInt(16) - 8), (double)level.getMinBuildHeight(), (double)(level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1));
        double randomZPosRange = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
        if (livingEntity.isPassenger()) {
            livingEntity.stopRiding();
        }

        Vec3 entityPos = livingEntity.position();
        if (livingEntity.randomTeleport(randomXPosRange, randomYPosRange, randomZPosRange, true)) {
            level.gameEvent(GameEvent.TELEPORT, entityPos, GameEvent.Context.of(livingEntity));
            level.playSound((Player)null, BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL);
            livingEntity.resetFallDistance();
            return true;
        }
        else {
            level.gameEvent(GameEvent.TELEPORT, entityPos, GameEvent.Context.of(livingEntity));
            level.playSound((Player)null, BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL);
            livingEntity.resetFallDistance();
            return false;
        }

    }
}
