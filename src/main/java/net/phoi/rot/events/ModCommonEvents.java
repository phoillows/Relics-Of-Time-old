package net.phoi.rot.events;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.datagen.*;
import net.phoi.rot.datagen.tag.ModBiomeTagProvider;
import net.phoi.rot.datagen.tag.ModBlockTagProvider;
import net.phoi.rot.datagen.tag.ModItemTagProvider;
import net.phoi.rot.level.entity.*;

import static net.phoi.rot.registry.EntityRegistry.*;

@Mod.EventBusSubscriber(modid = RelicsOfTime.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(FURCACAUDA.get(), Furcacauda.createAttributes().build());
        event.put(CONCAVENATOR.get(), Concavenator.createAttributes().build());
        event.put(PLATYHYSTRIX.get(), Platyhystrix.createAttributes().build());
        event.put(PROTOCERATOPS.get(), Protoceratops.createAttributes().build());
        event.put(POSTOSUCHUS.get(), Postosuchus.createAttributes().build());
        event.put(DUNKLEOSTEUS.get(), Dunkleosteus.createAttributes().build());
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        ModBlockTagProvider blockTagProvider = new ModBlockTagProvider(generator, fileHelper);

        generator.addProvider(true, new ModBlockStateProvider(generator, fileHelper));
        generator.addProvider(true, new ModItemModelProvider(generator, fileHelper));
        generator.addProvider(true, new ModLanguageProvider(generator));
        generator.addProvider(true, blockTagProvider);
        generator.addProvider(true, new ModItemTagProvider(generator, blockTagProvider, fileHelper));
        generator.addProvider(true, new ModBiomeTagProvider(generator, fileHelper));
    }
}
