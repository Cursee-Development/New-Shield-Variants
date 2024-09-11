package com.cursee.new_shield_variants.core.event;

import com.cursee.new_shield_variants.Constants;
import com.cursee.new_shield_variants.core.registry.GlobalRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class NSVEnderShieldBlockEvent {

    public static boolean randomTeleport(LivingEntity $$2) {

        Level $$1 = $$2.level();

//        for(int $$4 = 0; $$4 < 16; ++$$4) {
        if (true) {
            double $$5 = $$2.getX() + ($$2.getRandom().nextDouble() - 0.5) * 16.0;
            double $$6 = Mth.clamp($$2.getY() + (double)($$2.getRandom().nextInt(16) - 8), (double)$$1.getMinBuildHeight(), (double)($$1.getMinBuildHeight() + ((ServerLevel)$$1).getLogicalHeight() - 1));
            double $$7 = $$2.getZ() + ($$2.getRandom().nextDouble() - 0.5) * 16.0;
            if ($$2.isPassenger()) {
                $$2.stopRiding();
            }

            Vec3 $$8 = $$2.position();
            if ($$2.randomTeleport($$5, $$6, $$7, true)) {

                $$1.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of($$2));
                SoundSource $$12 = SoundSource.NEUTRAL;
                SoundEvent $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;

                $$1.playSound((Player)null, BlockPos.containing($$2.getX(), $$2.getY(), $$2.getZ()), $$11, $$12);
                $$2.resetFallDistance();
                return true;
            } else {

                $$1.gameEvent(GameEvent.TELEPORT, $$8, GameEvent.Context.of($$2));
                SoundSource $$12 = SoundSource.NEUTRAL;
                SoundEvent $$11 = SoundEvents.CHORUS_FRUIT_TELEPORT;

                $$1.playSound((Player)null, BlockPos.containing($$2.getX(), $$2.getY(), $$2.getZ()), $$11, $$12);
                $$2.resetFallDistance();
                return false;
            }
        }

        return false;
    }

    @SubscribeEvent
    public static void shieldBlockEvent(ShieldBlockEvent event) {
//        NewShieldVariants.LOGGER.info("\n event.getEntity getDisplayName: \n" + event.getEntity().getDisplayName().toString()); // contains Dev
//        NewShieldVariants.LOGGER.info("\n event.getDamageSource getEntity getDisplayName: \n" + event.getDamageSource().getEntity().getDisplayName().toString());

        if (event.getDamageSource().getEntity() instanceof LivingEntity) {
            LivingEntity blockingEntity = event.getEntity();
            LivingEntity attackingEntity = (LivingEntity) event.getDamageSource().getEntity();

            if (blockingEntity instanceof Player player && attackingEntity != null) {
                if (player.getMainHandItem().getItem() == GlobalRegistry.ENDER_SHIELD.get() || player.getOffhandItem().getItem() == GlobalRegistry.ENDER_SHIELD.get()) {
//                attackingEntity.randomTeleport(10, 2, 10, false);
                    randomTeleport(attackingEntity);
                    event.shieldTakesDamage();
                }
            }
        }

//        if (
//            event.getEntity() instanceof Player &&
//            (
//                event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().contains(Component.literal("Ender Shield"))
//                ||
//                event.getEntity().getItemInHand(InteractionHand.OFF_HAND).getDisplayName().contains(Component.literal("Ender Shield")) )
//        ) {
//            Entity attacker = event.getDamageSource().getEntity();
//
//            attacker.teleportTo(attacker.xOld + (new Random().nextDouble() * 8), attacker.getRandomY(), attacker.zOld + (new Random().nextDouble() * 8));
//            event.shieldTakesDamage();
//        }

    }
}
