package net.phoi.rot.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.client.screen.*;

public class MenuTypesRegistry {
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RelicsOfTime.MODID);

    public static final RegistryObject<MenuType<DnaAnaylzerMenu>> DNA_ANAYLZER_MENU = MENU.register("dna_anaylzer", () -> IForgeMenuType.create(DnaAnaylzerMenu::new));
}
