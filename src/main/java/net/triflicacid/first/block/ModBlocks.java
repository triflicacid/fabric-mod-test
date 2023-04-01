package net.triflicacid.first.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.triflicacid.first.FirstMod;
import net.triflicacid.first.block.custom.CaesiumBombBlock;
import net.triflicacid.first.block.custom.PluriumOreBlock;
import net.triflicacid.first.block.custom.SightBlock;
import net.triflicacid.first.block.custom.UraniumLampBlock;
import net.triflicacid.first.item.ModItemGroup;

public class ModBlocks {

    public static final Block PLURIUM_ORE = registerBlock("plurium_ore", new PluriumOreBlock(FabricBlockSettings.of(Material.METAL).luminance(st -> st.get(UraniumLampBlock.LIT) ? 9 : 2).strength(2f).requiresTool()), ModItemGroup.STUFF);
    public static final Block CAESIUM_ORE = registerBlock("caesium_ore", new Block(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool()), ModItemGroup.STUFF);
    public static final Block CAESIUM_BOMB = registerBlock("caesium_bomb", new CaesiumBombBlock(FabricBlockSettings.of(Material.TNT)), ModItemGroup.STUFF);
    public static final Block URANIUM_BLOCK = registerBlock("uranium_block", new Block(FabricBlockSettings.of(Material.GLASS).strength(5f).luminance(10).requiresTool()), ModItemGroup.STUFF);
    public static final Block URANIUM_LAMP = registerBlock("uranium_lamp", new UraniumLampBlock(FabricBlockSettings.of(Material.GLASS).luminance(st -> st.get(UraniumLampBlock.LIT) ? 15 : 0)), ModItemGroup.STUFF);
    public static final Block DICE_BLOCK = registerBlock("dice_block", new Block(FabricBlockSettings.of(Material.GLASS).sounds(BlockSoundGroup.GLASS)));
    public static final Block SIGHT_BLOCK = registerBlock("sight_block", new SightBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE)), ModItemGroup.STUFF);

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

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(FirstMod.MOD_ID, name), block);
    }
}
