package net.phoi.rot.level.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
            }
            if (concav.stunAmount >= 2) {
                concav.tame((Player)pAttacker);
                ((Player)pAttacker).displayClientMessage(Component.literal("You've tamed a Concavenator!").withStyle(ChatFormatting.BOLD), false);
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
