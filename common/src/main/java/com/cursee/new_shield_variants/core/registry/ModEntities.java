package com.cursee.new_shield_variants.core.registry;

import com.cursee.new_shield_variants.NewShieldVariants;
import com.cursee.new_shield_variants.core.world.entity.projectile.ThrownFireChargeFabric;
import com.cursee.new_shield_variants.core.world.entity.projectile.ThrownTNTFabric;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.BiConsumer;

public class ModEntities {

    public static final EntityType<ThrownTNTFabric> THROWN_TNT = EntityType.Builder.<ThrownTNTFabric>of(ThrownTNTFabric::new, MobCategory.MISC).build("new_shield_variants:ignored_thrown_tnt");
    public static final EntityType<ThrownFireChargeFabric> THROWN_FIRE_CHARGE = EntityType.Builder.<ThrownFireChargeFabric>of(ThrownFireChargeFabric::new, MobCategory.MISC).build("new_shield_variants:ignored_thrown_fire_charge");

    public static void register(BiConsumer<EntityType<?>, ResourceLocation> consumer) {
        consumer.accept(THROWN_TNT, NewShieldVariants.identifier("ignored_thrown_tnt"));
        consumer.accept(THROWN_FIRE_CHARGE, NewShieldVariants.identifier("ignored_thrown_fire_charge"));
    }
}
