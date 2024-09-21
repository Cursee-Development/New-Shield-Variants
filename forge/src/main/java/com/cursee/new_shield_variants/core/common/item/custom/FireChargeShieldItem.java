package com.cursee.new_shield_variants.core.common.item.custom;

import com.cursee.new_shield_variants.core.common.projectile.custom.NSVThrownFireCharge;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;

public class FireChargeShieldItem extends ShieldItem {
    public FireChargeShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        if (!level.isClientSide) {
            NSVThrownFireCharge thrownegg = new NSVThrownFireCharge(level, player);
            thrownegg.setItem(Items.FIRE_CHARGE.getDefaultInstance());
            thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(thrownegg);
        }
        itemstack.setDamageValue(itemstack.getDamageValue() - 20);

        player.getCooldowns().addCooldown(this, 20);

        return InteractionResultHolder.consume(itemstack);
    }
}