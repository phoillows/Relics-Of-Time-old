package net.phoi.rot.client.gui.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.phoi.rot.level.block.entity.DnaAnaylzerBlockEntity;
import net.phoi.rot.level.item.DNABottleItem;
import net.phoi.rot.registry.BlockRegistry;
import net.phoi.rot.registry.ItemRegistry;
import net.phoi.rot.registry.MenuTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class DnaAnaylzerMenu extends AbstractContainerMenu {
    public final DnaAnaylzerBlockEntity blockEntity;
    private final Level level;

    // Client
    public DnaAnaylzerMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    // Server
    public DnaAnaylzerMenu(int id, Inventory inventory, BlockEntity blockEntity) {
        super(MenuTypesRegistry.DNA_ANAYLZER_MENU.get(), id);
        checkContainerSize(inventory, 5);
        addPlayerInventory(inventory);
        this.blockEntity = (DnaAnaylzerBlockEntity)blockEntity;
        this.level = inventory.player.level;

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 58, 12));
            this.addSlot(new SlotItemHandler(handler, 1, 103, 12) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.is(ItemRegistry.MESOZOIC_FOSSIL.get()) || stack.is(ItemRegistry.PALEOZOIC_FOSSIL.get()) || stack.is(ItemRegistry.PLANT_FOSSIL.get());
                }
            });
            this.addSlot(new SlotItemHandler(handler, 2, 53, 57));
            this.addSlot(new SlotItemHandler(handler, 3, 80, 57));
            this.addSlot(new SlotItemHandler(handler, 4, 107, 57));
        });
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 5;  // must be the number of slots you have!

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
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockRegistry.DNA_ANALYZER.get());
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
