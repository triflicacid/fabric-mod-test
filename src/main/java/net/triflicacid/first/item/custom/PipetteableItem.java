package net.triflicacid.first.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.triflicacid.first.block.ModBlocks;
import net.triflicacid.first.item.ModItems;

public class PipetteableItem extends Item {
    public PipetteableItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient() && context.getHand() == Hand.MAIN_HAND) {
            BlockPos pos = context.getBlockPos();
            Block block = context.getWorld().getBlockState(pos).getBlock();

            if (block == ModBlocks.CAESIUM_ORE) {
                // Remove a vial
                ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
                if (stack.getCount() > 0) {
                    stack.decrement(1);
                    // Give player a caesium vial
                    int slot = context.getPlayer().getInventory().getEmptySlot();
                    if (slot == -1) {
                        context.getPlayer().dropItem(ModItems.PIPETTE_OF_CAESIUM);
                    } else {
                        context.getPlayer().getInventory().setStack(slot, new ItemStack(ModItems.PIPETTE_OF_CAESIUM));
                    }
                    // Set to stone
                    context.getWorld().removeBlock(pos, false);
                    context.getWorld().setBlockState(pos, Registry.BLOCK.get(new Identifier("minecraft", "stone")).getDefaultState());
                }
            }
        }

        return super.useOnBlock(context);
    }
}
