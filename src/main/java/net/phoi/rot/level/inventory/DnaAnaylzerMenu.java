package net.phoi.rot.level.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.phoi.rot.level.block.entity.DnaAnaylzerBlockEntity;
import net.phoi.rot.registry.BlockRegistry;
import net.phoi.rot.registry.ItemRegistry;
import net.phoi.rot.registry.MenuTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class DnaAnaylzerMenu extends BaseContainerMenu {
    public final DnaAnaylzerBlockEntity blockEntity;
    private final Level level;

    public DnaAnaylzerMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public DnaAnaylzerMenu(int id, Inventory inventory, BlockEntity blockEntity) {
        super(MenuTypesRegistry.DNA_ANAYLZER_MENU.get(), id);
        this.blockEntity = (DnaAnaylzerBlockEntity)blockEntity;
        this.level = inventory.player.level;
        this.addMenuSlots();
        this.addPlayerInventory(inventory);
        this.addPlayerHotbar(inventory);
    }

    @Override
    protected int getSlots() {
        return 5;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockRegistry.DNA_ANALYZER.get());
    }

    private void addMenuSlots() {
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
}
