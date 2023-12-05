package net.phoi.rot.level.item;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.phoi.rot.level.entity.Concavenator;

public class ArchaeopterisClubItem extends SwordItem {
    public ArchaeopterisClubItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pTarget instanceof Concavenator concav && !concav.isTame() && !concav.isBaby()) {
            RandomSource random = RandomSource.create();
            if (random.nextInt(3) == 1) {
                concav.setStunned(true);
                concav.stunAmount++;
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
