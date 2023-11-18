package net.phoi.rot.level.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.registry.MenuTypesRegistry;

public class ConcavenatorMenu extends AbstractContainerMenu {
    private final Container container;
    public final Concavenator concav;
    public final Level level;
    private int slotId = 1;

    public ConcavenatorMenu(int id, Inventory playerInventory) {
        this(id, new SimpleContainer(16), playerInventory, null);
    }

    public ConcavenatorMenu(int id, Container container, Inventory playerInventory, Concavenator concav) {
        super(MenuTypesRegistry.CONCAVENATOR_MENU.get(), id);
        this.container = container;
        this.concav = concav;
        this.level = playerInventory.player.level;
        this.addPlayerInventory(playerInventory);
        this.addSlot(new Slot(container, 0, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return pStack.is(Items.SADDLE);
            }

            @Override
            public void setChanged() {
                if (ConcavenatorMenu.this.concav != null) {
                    ConcavenatorMenu.this.concav.setSaddled(this.getItem().is(Items.SADDLE));
                }
                super.setChanged();
            }
        });
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                this.addSlot(new Slot(container, slotId++, 80 + y * 18, 18 + x * 18));
            }
        }
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 16;  // must be the number of slots you have!

    // Credit to diesieben07 for the code.
    // In short term, the code below is a bunch of math that calculates when the player shift clicks on a slot, what slot should the item end up in
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
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

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.concav.isAlive();
    }

    private void addPlayerInventory(Inventory inventory) {
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
