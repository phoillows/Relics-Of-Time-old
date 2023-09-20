package net.phoi.rot.events;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.client.gui.*;
import net.phoi.rot.client.renderer.*;
import net.phoi.rot.level.block.ModWoodTypes;
import net.phoi.rot.registry.BlockEntityRegistry;
import net.phoi.rot.registry.MenuTypesRegistry;
import static net.phoi.rot.registry.EntityRegistry.*;

@Mod.EventBusSubscriber(modid = RelicsOfTime.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(MenuTypesRegistry.DNA_ANAYLZER_MENU.get(), DnaAnaylzerScreen::new);
        MenuScreens.register(MenuTypesRegistry.DNA_CENTRIFUGE_MENU.get(), DnaCentrifugeScreen::new);

        WoodType.register(ModWoodTypes.ARCHAEOPTERIS);
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Entity renderers
        event.registerEntityRenderer(FURCACAUDA.get(), FurcacaudaRenderer::new);
        event.registerEntityRenderer(CONCAVENATOR.get(), ConcavenatorRenderer::new);
        event.registerEntityRenderer(PLATYHYSTRIX.get(), PlatyhystrixRenderer::new);

        // Block entity renderers
        event.registerBlockEntityRenderer(BlockEntityRegistry.SIGN_BLOCK_ENTITY.get(), SignRenderer::new);
    }
}
