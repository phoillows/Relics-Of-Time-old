package net.phoi.rot.level.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class BaseMenu extends AbstractContainerMenu {
    protected BaseMenu(@Nullable MenuType<?> pMenuType, int pContainerId, Inventory inventory, boolean hasPlayerInv) {
        super(pMenuType, pContainerId);
        if (hasPlayerInv) {
            // Player inventory
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 9; y++) {
                    this.addSlot(new Slot(inventory, y + x * 9 + 9, 8 + y * 18, 84 + x * 18));
                }
            }

            // Player hotbar
            for (int x = 0; x < 9; x++) {
                this.addSlot(new Slot(inventory, x, 8 + x * 18, 142));
            }
        }
    }

    protected abstract int getSlots();

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < 9 + 9 * 3) {
            if (!moveItemStackTo(sourceStack, 9 + 9 * 3, 9 + 9 * 3 + this.getSlots(), false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < 9 + 9 * 3 + this.getSlots()) {
            if (!moveItemStackTo(sourceStack, 0, 9 + 9 * 3, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
}
