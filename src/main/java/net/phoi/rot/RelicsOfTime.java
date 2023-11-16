package net.phoi.rot;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.phoi.rot.util.ModWoodTypes;
import net.phoi.rot.registry.*;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RelicsOfTime.MODID)
public class RelicsOfTime {
    public static final String MODID = "rot";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreativeModeTab RELICS_OF_TIME = new CreativeModeTab("relics_of_time") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.AMBER.get());
        }
    };

    public RelicsOfTime() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockEntityRegistry.BLOCK_ENTITY.register(modEventBus);
        BlockRegistry.BLOCK.register(modEventBus);
        ConfiguredFeatureRegistry.CONFIGURED_FEATURE.register(modEventBus);
        EntityRegistry.ENTITY.register(modEventBus);
        ItemRegistry.ITEM.register(modEventBus);
        MenuTypesRegistry.MENU.register(modEventBus);
        MobEffectRegistry.MOB_EFFECT.register(modEventBus);
        PaintingRegistry.PAINTING.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.ARCHAEOPTERIS);
        });
    }
}
