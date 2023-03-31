package net.triflicacid.first.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.triflicacid.first.FirstMod;
import net.triflicacid.first.item.ModItemGroup;

public class ModBlocks {

    public static final Block PLURIUM_ORE = registerBlock("plurium_ore", new Block(FabricBlockSettings.of(Material.METAL).luminance(4).strength(2f)), ModItemGroup.STUFF);
    public static final Block CAESIUM_ORE = registerBlock("caesium_ore", new Block(FabricBlockSettings.of(Material.METAL).strength(2f)), ModItemGroup.STUFF);

    public static void registerModBlocks() {
        FirstMod.LOGGER.debug(FirstMod.MOD_ID + ": registering blocks");
    }

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(FirstMod.MOD_ID, name), block);
    }

    private static Item registerItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(FirstMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }
}
