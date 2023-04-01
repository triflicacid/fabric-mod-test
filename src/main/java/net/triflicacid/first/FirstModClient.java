package net.triflicacid.first;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.triflicacid.first.block.ModBlocks;

public class FirstModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Make sure textures are transparent
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAPESEED_CROP, RenderLayer.getCutout());
    }
}
