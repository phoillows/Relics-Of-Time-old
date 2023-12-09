package net.phoi.rot.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.phoi.rot.level.entity.*;
import net.phoi.rot.level.inventory.DnaCentrifugeMenu;
import net.phoi.rot.registry.EntityRegistry;
import net.phoi.rot.registry.ItemRegistry;
import net.phoi.rot.util.Helper;

public class DnaCentrifugeScreen extends AbstractContainerScreen<DnaCentrifugeMenu> {
    private static final ResourceLocation TEXTURE = Helper.createPath("textures/gui/dna_centrifuge.png");
    private final DnaCentrifugeMenu menu;
    private final Level level;

    public DnaCentrifugeScreen(DnaCentrifugeMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.menu = menu;
        this.level = menu.level;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
        AgeableMob mob = null;
        if (this.checkEgg(ItemRegistry.CONCAVENATOR_DNA.get())) {
            mob = new Concavenator(EntityRegistry.CONCAVENATOR.get(), this.level);
            InventoryScreen.renderEntityInInventory(x + 42, y + 62, 20, (float)(x + 42) - mouseX, (float)(y + 75 - 50) - mouseY, mob);

        } else if (this.checkEgg(ItemRegistry.PROTOCERATOPS_DNA.get())) {
            mob = new Protoceratops(EntityRegistry.PROTOCERATOPS.get(), this.level);
            InventoryScreen.renderEntityInInventory(x + 42, y + 57, 35, (float)(x + 42) - mouseX, (float)(y + 75 - 50) - mouseY, mob);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }

    private boolean checkEgg(Item item) {
        return menu.blockEntity.itemHandler.getStackInSlot(0).is(item);
    }
}
