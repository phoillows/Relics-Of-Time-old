package net.phoi.rot.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.phoi.rot.RelicsOfTime;
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
    }
}
