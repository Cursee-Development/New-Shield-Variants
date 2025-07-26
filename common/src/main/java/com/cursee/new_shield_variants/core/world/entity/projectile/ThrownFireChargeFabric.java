package com.cursee.new_shield_variants.core.world.entity.projectile;

import com.cursee.new_shield_variants.core.registry.ModEntities;
import com.cursee.new_shield_variants.platform.Services;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownFireChargeFabric extends ThrowableItemProjectile {

    public ThrownFireChargeFabric(EntityType<? extends ThrowableItemProjectile> p_37473_, Level p_37474_) {
        super(p_37473_, p_37474_);
    }

    public ThrownFireChargeFabric(Level p_37481_, LivingEntity p_37482_) {
        super(ModEntities.THROWN_FIRE_CHARGE, p_37482_, p_37481_);
        setItem(Items.FIRE_CHARGE.getDefaultInstance());
        this.setItemSlot(EquipmentSlot.MAINHAND, Items.FIRE_CHARGE.getDefaultInstance());
        if (Services.PLATFORM.isDevelopmentEnvironment()) System.out.println("created ThrownFireChargeFabric instance");
    }

    public ThrownFireChargeFabric(Level p_37476_, double p_37477_, double p_37478_, double p_37479_) {
        super(ModEntities.THROWN_FIRE_CHARGE, p_37477_, p_37478_, p_37479_, p_37476_);
    }

    public void handleEntityEvent(byte p_37484_) {
        if (p_37484_ == EntityEvent.DEATH) {
            double d0 = 0.08;

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, Items.FIRE_CHARGE.getDefaultInstance()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08);
            }
        }

    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().setRemainingFireTicks(4 * 20);
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {
//            Level level = this.level();
//            BlockPos blockpos = this.blockPosition().relative(this.getDirection());
//            PrimedTnt primedtnt = new PrimedTnt(level, (double)blockpos.getX() + (double)0.5F, (double)blockpos.getY(), (double)blockpos.getZ() + (double)0.5F, (LivingEntity)null);
//            level.addFreshEntity(primedtnt);
//            level.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
//            level.gameEvent((Entity)null, GameEvent.ENTITY_PLACE, blockpos);

            BlockPos blockpos = this.blockPosition().relative(this.getDirection());
            if (BaseFireBlock.canBePlacedAt(this.level(), blockpos, this.getDirection())) {
                BlockState blockstate = BaseFireBlock.getState(this.level(), blockpos);
                this.level().setBlock(blockpos, blockstate, 11);
            }

            this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
            this.discard();
        }

    }

    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0, 0));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}

