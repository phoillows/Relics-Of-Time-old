package net.phoi.rot.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;

import java.util.List;

public class PlacedFeatureRegistry {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, RelicsOfTime.MODID);

    public static final RegistryObject<PlacedFeature> AMBER_ORE = PLACED_FEATURE.register("amber_ore", () ->
            new PlacedFeature(ConfiguredFeatureRegistry.AMBER_ORE.getHolder().get(), commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(4), VerticalAnchor.absolute(64)))));

    public static final RegistryObject<PlacedFeature> FOSSIL_ORE = PLACED_FEATURE.register("fossil_ore", () ->
            new PlacedFeature(ConfiguredFeatureRegistry.FOSSIL_ORE.getHolder().get(), commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(4), VerticalAnchor.absolute(64)))));


    /** Helper methods **/
    private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }
}
