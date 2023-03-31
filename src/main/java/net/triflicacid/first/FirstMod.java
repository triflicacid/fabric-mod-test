package net.triflicacid.first;

import net.fabricmc.api.ModInitializer;
import net.triflicacid.first.block.ModBlocks;
import net.triflicacid.first.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstMod implements ModInitializer {
	public static final String MOD_ID = "first";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
