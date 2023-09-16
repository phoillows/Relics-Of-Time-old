package net.phoi.rot.level.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DnaBottleItem extends GenericItem {
    private final String text;

    public DnaBottleItem(String text) {
        super();
        this.text = text;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("item.rot.dna_bottle");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§9" + text));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
