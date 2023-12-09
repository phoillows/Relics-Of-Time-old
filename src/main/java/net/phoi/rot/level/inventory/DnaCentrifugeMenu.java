package net.phoi.rot.level.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.phoi.rot.level.block.entity.DnaCentrifugeBlockEntity;
import net.phoi.rot.level.item.DnaBottleItem;
import net.phoi.rot.registry.BlockRegistry;
import net.phoi.rot.registry.MenuTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class DnaCentrifugeMenu extends BaseMenu {
    public final DnaCentrifugeBlockEntity blockEntity;
    public final Level level;

    public DnaCentrifugeMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public DnaCentrifugeMenu(int id, Inventory inventory, BlockEntity blockEntity) {
        super(MenuTypesRegistry.DNA_CENTRIFUGE_MENU.get(), id, inventory, true);
        checkContainerSize(inventory, 3);
        this.blockEntity = (DnaCentrifugeBlockEntity)blockEntity;
        this.level = inventory.player.level;
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 80, 15) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.getItem() instanceof DnaBottleItem;
                }
            });
            this.addSlot(new SlotItemHandler(handler, 1, 80, 52) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.is(Items.MILK_BUCKET);
                }
            });
            this.addSlot(new SlotItemHandler(handler, 2, 138, 32){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
        });
    }

    @Override
    protected int getSlots() {
        return 3;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockRegistry.DNA_CENTRIFUGE.get());
    }
}
