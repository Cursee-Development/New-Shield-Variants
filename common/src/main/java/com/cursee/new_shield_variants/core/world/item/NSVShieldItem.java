package com.cursee.new_shield_variants.core.world.item;

import com.cursee.new_shield_variants.core.NSVConfig;
import com.cursee.new_shield_variants.platform.Services;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class NSVShieldItem extends ShieldItem {

    public final ShieldVariant variant;

    public NSVShieldItem(ShieldVariant variant, Properties properties) {
        super(properties);
        this.variant = variant;

        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);

        if (Services.PLATFORM.isClientSide()) this.registerItemProperty();
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return this.variant.getRepairIngredient().test(repair);
    }

    private void registerItemProperty() {

        ItemProperties.register(this, new ResourceLocation("blocking"), (itemStack, clientWorld, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F; // ????
        });
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return !NSVConfig.BANNED_SHIELDS.contains(this.variant.name().toLowerCase() + "_shield");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        NSVUseInteractions.handle(this, this.variant, level, player, itemstack, hand);

        return InteractionResultHolder.consume(itemstack);
    }
}
