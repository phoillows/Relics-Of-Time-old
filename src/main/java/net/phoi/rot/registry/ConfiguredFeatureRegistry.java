package net.phoi.rot.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.phoi.rot.RelicsOfTime;

import java.util.List;
import java.util.function.Supplier;

public class ConfiguredFeatureRegistry {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, RelicsOfTime.MODID);


    public static final RegistryObject<ConfiguredFeature<?, ?>> ARCHAEOPTERIS = CONFIGURED_FEATURE.register("archaeopteris", () ->
            new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockRegistry.ARCHAEOPTERIS_LOG.get()),
                    new StraightTrunkPlacer(8, 0, 0),
                    BlockStateProvider.simple(BlockRegistry.ARCHAEOPTERIS_LEAVES.get()),
                    new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                    new TwoLayersFeatureSize(0, 0, 0)).build()));

    private static final Supplier<List<OreConfiguration.TargetBlockState>> AMBER_ORE_TARGET = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.AMBER_ORE.get().defaultBlockState())));
    private static final Supplier<List<OreConfiguration.TargetBlockState>> FOSSIL_ORE_TARGET = Suppliers.memoize(() -> List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.FOSSIL_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> AMBER_ORE = CONFIGURED_FEATURE.register("amber_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(AMBER_ORE_TARGET.get(), 2)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FOSSIL_ORE = CONFIGURED_FEATURE.register("fossil_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(FOSSIL_ORE_TARGET.get(), 5)));
}
