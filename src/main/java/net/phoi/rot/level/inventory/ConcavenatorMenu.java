package net.phoi.rot.level.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.registry.MenuTypesRegistry;

public class ConcavenatorMenu extends BaseContainerMenu {
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
        this.addMenuSlots();
        this.addPlayerInventory(playerInventory);
        this.addPlayerHotbar(playerInventory);
    }

    @Override
    protected int getSlots() {
        return 16;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.concav.isAlive();
    }

    private void addMenuSlots() {
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
}
