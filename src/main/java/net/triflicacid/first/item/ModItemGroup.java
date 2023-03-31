package net.triflicacid.first.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.triflicacid.first.FirstMod;

public class ModItemGroup {
    public static final ItemGroup STUFF = FabricItemGroupBuilder.build(new Identifier(FirstMod.MOD_ID, "stuff"), () -> new ItemStack(ModItems.URANIUM_INGOT));
}
