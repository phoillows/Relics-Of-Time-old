package net.phoi.rot;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.phoi.rot.level.entity.ModPaintings;
import net.phoi.rot.registry.*;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RelicsOfTime.MODID)
public class RelicsOfTime {
    public static final String MODID = "rot";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreativeModeTab ROT_TAB = new CreativeModeTab("relics_of_time") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.AMBER.get());
        }
    };

    public RelicsOfTime() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityRegistry.ENTITY.register(modEventBus);  // Register entities
        ModPaintings.PAINTING.register(modEventBus);  // Register paintings
        ItemRegistry.ITEM.register(modEventBus);  // Register items

        BlockRegistry.BLOCK.register(modEventBus);  // Register blocks
        BlockEntityRegistry.BLOCK_ENTITY.register(modEventBus);  // Register block entities

        MenuTypesRegistry.MENU.register(modEventBus);  // Register menus

        modEventBus.addListener(this::commonSetup);  // Register the commonSetup method for modloading
        MinecraftForge.EVENT_BUS.register(this);  // Register ourselves for server and other game events we are interested in
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
