package com.cursee.new_shield_variants.core.common.projectile.custom;

//import net.jason13.newshieldvariants.registry.GlobalRegistry;
import com.cursee.new_shield_variants.core.common.registry.ModItemsFabric;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownFireChargeFabric extends ThrowableItemProjectile {
    public ThrownFireChargeFabric(EntityType<? extends net.minecraft.world.entity.projectile.ThrownEgg> p_37473_, Level p_37474_) {
        super(p_37473_, p_37474_);
    }

    public ThrownFireChargeFabric(Level p_37481_, LivingEntity p_37482_) {
        super(EntityType.EGG, p_37482_, p_37481_);
    }

    public ThrownFireChargeFabric(Level p_37476_, double p_37477_, double p_37478_, double p_37479_) {
        super(EntityType.EGG, p_37477_, p_37478_, p_37479_, p_37476_);
    }

    @Override
    public void handleEntityEvent(byte p_37484_) {
        if (p_37484_ == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
//                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, Items.FIRE_CHARGE.getDefaultInstance()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    @Override
    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().setRemainingFireTicks(4*20);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!this.level().isClientSide) {
            BlockPos blockpos = this.blockPosition().relative(this.getDirection());
            if (BaseFireBlock.canBePlacedAt(this.level(), blockpos, this.getDirection())) {
                BlockState blockstate = BaseFireBlock.getState(this.level(), blockpos);
                this.level().setBlock(blockpos, blockstate, 11);
            }

                // chicken spawning
//            if (this.random.nextInt(8) == 0) {
//                int i = 1;
//                if (this.random.nextInt(32) == 0) {
//                    i = 4;
//                }
//
//                for(int j = 0; j < i; ++j) {
//                    Chicken chicken = EntityType.CHICKEN.create(this.level());
//                    if (chicken != null) {
//                        chicken.setAge(-24000);
//                        chicken.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
//                        this.level().addFreshEntity(chicken);
//                    }
//                }
//            }

            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }


        // flint and steel fire spawn
//        static void {
//            BlockPos blockpos1 = this.blockPosition().relative(this.getDirection());
//            if (BaseFireBlock.canBePlacedAt(this.level(), blockpos1, this.getDirection())) {
//                this.level().playSound(this.createCommandSourceStack().getPlayer(), blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, this.level().getRandom().nextFloat() * 0.4F + 0.8F);
//                BlockState blockstate1 = BaseFireBlock.getState(this.level(), blockpos1);
//                this.level().setBlock(blockpos1, blockstate1, 11);
//                this.level().gameEvent(this.createCommandSourceStack().getPlayer(), GameEvent.BLOCK_PLACE, blockpos);
//                ItemStack itemstack = p_41297_.getItemInHand();
//
//
//
//                return InteractionResult.sidedSuccess(level.isClientSide());
//            }
//        }

    }

    @Override
    protected Item getDefaultItem() {
        return ModItemsFabric.FIRE_CHARGE_SHIELD;
    }
}