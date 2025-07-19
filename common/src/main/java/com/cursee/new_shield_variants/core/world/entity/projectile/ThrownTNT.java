package com.cursee.new_shield_variants.core.world.entity.projectile;

import com.cursee.new_shield_variants.core.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ThrownTNT extends ThrownEgg {

    public ThrownTNT(EntityType<? extends ThrownEgg> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownTNT(Level level, LivingEntity shooter) {
        super(level, shooter);
    }

    public ThrownTNT(Level level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.TNT;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == EntityEvent.DEATH) {
            double delta = 0.08D;

            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(
                        new ItemParticleOption(ParticleTypes.ITEM, Items.FIRE_CHARGE.getDefaultInstance()),
                        this.getX(), this.getY(), this.getZ(),
                        (((double) this.random.nextFloat()) - 0.5D) * delta,
                        (((double) this.random.nextFloat()) - 0.5D) * delta,
                        (((double) this.random.nextFloat()) - 0.5D) * delta
                );
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        result.getEntity().setRemainingFireTicks( 4 * 20);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        Level level = this.level();

        if (!level.isClientSide()) {
            BlockPos pos = this.blockPosition().relative(this.getDirection());

            PrimedTnt tnt = new PrimedTnt(level, ((double) pos.getX()) + 0.5D, (double) pos.getY(), ((double) pos.getZ()) + 0.5D, null);
            level.addFreshEntity(tnt);
            level.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.PRIME_FUSE, pos);
        }

        level.broadcastEntityEvent(this, (byte) 3);
        this.discard();

    }
}
