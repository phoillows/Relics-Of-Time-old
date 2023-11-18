package net.phoi.rot.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.phoi.rot.level.entity.Concavenator;
import net.phoi.rot.level.inventory.ConcavenatorMenu;
import net.phoi.rot.registry.EntityRegistry;
import net.phoi.rot.util.Helper;

public class ConcavenatorScreen extends AbstractContainerScreen<ConcavenatorMenu> {
    private static final ResourceLocation TEXTURE = Helper.createPath("textures/gui/concavenator.png");
    private final ConcavenatorMenu menu;
    private final Level level;
    private int xMouse;
    private int yMouse;

    public ConcavenatorScreen(ConcavenatorMenu menu, Inventory inventory, Component component) {
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
        Concavenator concav = new Concavenator(EntityRegistry.CONCAVENATOR.get(), this.level);
        InventoryScreen.renderEntityInInventory(x + 51, y + 60, 17, (float)(x + 51) - this.xMouse, (float)(y + 75 - 50) - this.yMouse, concav);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        this.xMouse = mouseX;
        this.yMouse = mouseY;
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
