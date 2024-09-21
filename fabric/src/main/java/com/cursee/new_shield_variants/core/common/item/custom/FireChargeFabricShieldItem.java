package com.cursee.new_shield_variants.core.common.item.custom;

import com.cursee.new_shield_variants.core.common.projectile.custom.ThrownFireChargeFabric;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FireChargeFabricShieldItem extends FabricShieldItem {

    public FireChargeFabricShieldItem(Properties settings, int coolDownTicks, int enchantability, Item... repairItems) {
        super(settings, coolDownTicks, enchantability, repairItems);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        if (!level.isClientSide) {
            ThrownFireChargeFabric thrownegg = new ThrownFireChargeFabric(level, player);
            thrownegg.setItem(Items.FIRE_CHARGE.getDefaultInstance());
            thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(thrownegg);
        }
        itemstack.setDamageValue(itemstack.getDamageValue() - 20);

        player.getCooldowns().addCooldown(this, 20);

        return InteractionResultHolder.consume(itemstack);
    }
}
