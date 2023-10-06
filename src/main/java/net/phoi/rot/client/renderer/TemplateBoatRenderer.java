package net.phoi.rot.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.phoi.rot.RelicsOfTime;
import net.phoi.rot.level.entity.TemplateBoat;

import java.util.Map;
import java.util.stream.Stream;

public class TemplateBoatRenderer extends BoatRenderer {
    private final Map<TemplateBoat.Type, Pair<ResourceLocation, BoatModel>> boatResources;

    public TemplateBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
        this.boatResources = Stream.of(TemplateBoat.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> type,
                (type) -> Pair.of(new ResourceLocation(RelicsOfTime.MODID, getTextureLocation(type)),
                        this.createBoatModel(context, type, hasChest))));
    }

    private static String getTextureLocation(TemplateBoat.Type type) {
        return "textures/entity/boat/" + type.getName() + ".png";
    }

    private BoatModel createBoatModel(EntityRendererProvider.Context context, TemplateBoat.Type type, boolean hasChest) {
        ModelLayerLocation modellayerlocation = TemplateBoatRenderer.createBoatModelName(type);
        ModelPart modelpart = context.bakeLayer(modellayerlocation);
        return new BoatModel(modelpart, hasChest);
    }

    public static ModelLayerLocation createBoatModelName(TemplateBoat.Type type) {
        return createLocation("boat/" + type.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(new ResourceLocation(RelicsOfTime.MODID, path), model);
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        if (boat instanceof TemplateBoat templateBoat) {
            return this.boatResources.get(templateBoat.getVariant());
        } else {
            return null;
        }
    }
}
