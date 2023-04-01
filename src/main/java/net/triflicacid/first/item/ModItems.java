package net.triflicacid.first.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.triflicacid.first.FirstMod;
import net.triflicacid.first.block.ModBlocks;
import net.triflicacid.first.item.custom.DiceItem;
import net.triflicacid.first.item.custom.DrinkExplodeItem;
import net.triflicacid.first.item.custom.PipetteItem;
import net.triflicacid.first.item.custom.VialItem;

public class ModItems {
    /** Items */
    public static final Item DICE = registerItem("dice", new DiceItem(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item GLASS_VIAL = registerItem("glass_vial", new VialItem(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item PIPETTE = registerItem("pipette", new PipetteItem(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item PIPETTE_OF_WATER = registerItem("pipette_of_water", new Item(new FabricItemSettings().group(ModItemGroup.STUFF).maxCount(1).recipeRemainder(PIPETTE)));
    public static final Item PLURIUM_DUST = registerItem("plurium_dust", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item PLURIUM_NUGGET = registerItem("plurium_nugget", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item URANIUM_INGOT = registerItem("uranium_ingot", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item PLUTONIUM_INGOT = registerItem("plutonium_ingot", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item CAESIUM_DROPLET = registerItem("caesium_droplet", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item VIAL_OF_CAESIUM = registerItem("vial_of_caesium", new DrinkExplodeItem(new FabricItemSettings().group(ModItemGroup.STUFF).maxCount(1).recipeRemainder(GLASS_VIAL)));
    public static final Item VIAL_OF_WATER = registerItem("vial_of_water", new Item(new FabricItemSettings().group(ModItemGroup.STUFF).maxCount(1).recipeRemainder(GLASS_VIAL)));
    public static final Item VIAL_OF_RAPESEED_OIL = registerItem("vial_of_rapeseed_oil", new Item(new FabricItemSettings().group(ModItemGroup.STUFF).maxCount(1).recipeRemainder(GLASS_VIAL)));
    public static final Item PIPETTE_OF_CAESIUM = registerItem("pipette_of_caesium", new Item(new FabricItemSettings().group(ModItemGroup.STUFF).maxCount(1).recipeRemainder(PIPETTE)));
    public static final Item RAPESEED_SEEDS = registerItem("rapeseed_seeds", new AliasedBlockItem(ModBlocks.RAPESEED_CROP, new FabricItemSettings().group(ModItemGroup.STUFF)));

    public static void registerModItems() {
        FirstMod.LOGGER.debug(FirstMod.MOD_ID + ": registering items");
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(FirstMod.MOD_ID, name), item);
    }
}
