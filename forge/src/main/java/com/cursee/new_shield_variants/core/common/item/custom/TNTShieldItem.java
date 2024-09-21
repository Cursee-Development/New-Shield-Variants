package com.cursee.new_shield_variants.core.common.item.custom;

import com.cursee.new_shield_variants.core.common.projectile.custom.NSVThrownTNT;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;

public class TNTShieldItem extends ShieldItem {
    public TNTShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        if (!level.isClientSide) {
            NSVThrownTNT throwntnt = new NSVThrownTNT(level, player);
            throwntnt.setItem(Items.TNT.getDefaultInstance());
            throwntnt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(throwntnt);
        }
        itemstack.setDamageValue(itemstack.getDamageValue() - 20);

        return InteractionResultHolder.consume(itemstack);
    }
}