package com.cursee.new_shield_variants.core.common.item.custom;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class DragonHeadFabricShieldItem extends FabricShieldItem {

    public DragonHeadFabricShieldItem(Properties settings, int coolDownTicks, int enchantability, Item... repairItems) {
        super(settings, coolDownTicks, enchantability, repairItems);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);


        List<LivingEntity> list = player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(player.level(), player.xOld, player.yOld, player.zOld);

        areaeffectcloud.setOwner((LivingEntity)player);

        areaeffectcloud.setParticle(ParticleTypes.DRAGON_BREATH);
        areaeffectcloud.setRadius(3.0F);
        areaeffectcloud.setDuration(100);
        areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
        areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 50, 2));
        if (!list.isEmpty()) {
            for(LivingEntity livingentity : list) {
                double d0 = player.distanceToSqr(livingentity);
                if (d0 < 16.0D) {
                    areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                    break;
                }

            }
        }

        player.level().levelEvent(2006, player.blockPosition(), player.isSilent() ? -1 : 1);
        player.level().addFreshEntity(areaeffectcloud);




        player.getCooldowns().addCooldown(this, 100);

        return InteractionResultHolder.consume(itemstack);
    }
}
