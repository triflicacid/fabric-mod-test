package net.triflicacid.first.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.triflicacid.first.FirstMod;

public class ModItems {
    /** Items */
    public static final Item PLURIUM_NUGGET = registerItem("plurium_nugget", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item URANIUM_INGOT = registerItem("uranium_ingot", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item PLUTONIUM_INGOT = registerItem("plutonium_ingot", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item CAESIUM_DROPLET = registerItem("caesium_droplet", new Item(new FabricItemSettings().group(ModItemGroup.STUFF)));
    public static final Item VIAL_OF_CAESIUM = registerItem("vial_of_caesium", new Item(new FabricItemSettings().group(ModItemGroup.STUFF).maxCount(1)));

    public static void registerModItems() {
        FirstMod.LOGGER.debug(FirstMod.MOD_ID + ": registering items");
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(FirstMod.MOD_ID, name), item);
    }
}
