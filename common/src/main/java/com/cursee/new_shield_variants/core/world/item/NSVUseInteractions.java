package com.cursee.new_shield_variants.core.world.item;

import com.cursee.new_shield_variants.core.world.entity.projectile.ThrownFireChargeFabric;
import com.cursee.new_shield_variants.core.world.entity.projectile.ThrownTNTFabric;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NSVUseInteractions {

    public static void handle(NSVShieldItem shield, ShieldVariant variant, Level level, Player player, ItemStack itemstack, InteractionHand hand) {
        switch (variant) {
            case BLAZE -> NSVUseInteractions.blazeShield(shield, player);
            case DRAGON_HEAD -> NSVUseInteractions.dragonHeadShield(shield, player);
            case FIRE_CHARGE -> NSVUseInteractions.fireChargeShield(shield, level, player, hand);
            case TNT -> NSVUseInteractions.tntShield(shield, level, player, hand);
        }
    }

    private static void blazeShield(NSVShieldItem shield, Player player) {
        List<LivingEntity> list = player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));

        AtomicBoolean ignited = new AtomicBoolean(false);
        list.forEach(entity -> {

            boolean ignites = false;

            // instead of many if statements, we stop checking after the first one sets ignite to true
            if (entity instanceof Monster) ignites = true;
            else if (entity == player.getLastHurtByMob()) ignites = true;
            else if (entity.getLastHurtMob() == player) ignites = true;
            else if (entity.canAttack(player)) ignites = true;
            else if (entity.attackable() && (entity instanceof Player other && other.getTeam() != player.getTeam())) ignites = true;

            // make sure we don't ignite ourselves...
            if (entity == player) ignites = false;

            if (ignites) {
                if (!entity.isOnFire()) entity.setSecondsOnFire(2);
                ignited.set(true);
            }
        });

        if (ignited.get()) {
            player.getCooldowns().addCooldown(shield, 100);
        }
    }

    private static void dragonHeadShield(NSVShieldItem shield, Player player) {
        List<LivingEntity> list = player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(player.level(), player.xOld, player.yOld, player.zOld);

        areaeffectcloud.setOwner((LivingEntity)player);

        areaeffectcloud.setParticle(ParticleTypes.DRAGON_BREATH);
        areaeffectcloud.setRadius(3.0F);
        areaeffectcloud.setDuration(100);
        areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 50, 2));

        LivingEntity lastHurtBy = player.getLastHurtByMob();
        if (!list.isEmpty() || lastHurtBy != null) {
            if (lastHurtBy != null) {
                areaeffectcloud.setPos(lastHurtBy.getX(), lastHurtBy.getY(), lastHurtBy.getZ());
            }
            else {
                for (LivingEntity livingentity : list) {
                    double d0 = player.distanceToSqr(livingentity);
                    if (d0 < 16.0D) {
                        areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                        break;
                    }
                }
            }
        }

        player.level().levelEvent(LevelEvent.PARTICLES_DRAGON_FIREBALL_SPLASH, player.blockPosition(), player.isSilent() ? -1 : 1);
        player.level().addFreshEntity(areaeffectcloud);

        player.getCooldowns().addCooldown(shield, 100);
    }

    private static void fireChargeShield(NSVShieldItem shield, Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // todo: re-impl our old entity?
//            NSVThrownFireCharge thrownegg = new NSVThrownFireCharge(level, player);
//            thrownegg.setItem(Items.FIRE_CHARGE.getDefaultInstance());
//            thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
//            level.addFreshEntity(thrownegg);

            // LargeFireball fireball = new LargeFireball(EntityType.FIREBALL, level);
//            SmallFireball fireball = new SmallFireball(level, player, player.getX(), player.getY() + 1, player.getZ());
//            fireball.setItem(Items.FIRE_CHARGE.getDefaultInstance());
//            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
//            level.addFreshEntity(fireball);

            ThrownFireChargeFabric throwntnt = new ThrownFireChargeFabric(level, player);
            throwntnt.setItem(Items.FIRE_CHARGE.getDefaultInstance());
            throwntnt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(throwntnt);
        }
        itemstack.setDamageValue(itemstack.getDamageValue() - 20);

        player.getCooldowns().addCooldown(shield, 20);
    }

    private static void tntShield(NSVShieldItem shield, Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
//            ThrownTNT tnt = new ThrownTNT(player, level);
//            // tnt.setItem(Items.TNT.getDefaultInstance());
//            tnt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 1.0f);
//            level.addFreshEntity(tnt);

            ThrownTNTFabric throwntnt = new ThrownTNTFabric(level, player);
            throwntnt.setItem(Items.TNT.getDefaultInstance());
            throwntnt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(throwntnt);
        }

        itemstack.setDamageValue(itemstack.getDamageValue() - 20);

        player.getCooldowns().addCooldown(shield, 60);
    }
}
