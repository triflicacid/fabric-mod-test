package net.triflicacid.first.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.triflicacid.first.block.ModBlocks;
import net.triflicacid.first.item.ModItems;

public class PipetteableItem extends Item {
    public PipetteableItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // Using on a solid block?
        BlockHitResult hitResult = Item.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);

        if (((HitResult)hitResult).getType() == HitResult.Type.MISS)
            return TypedActionResult.pass(itemStack);

        if (((HitResult)hitResult).getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hitResult.getBlockPos();

            // caesium residue
            if (world.getBlockState(pos).getBlock() == ModBlocks.CAESIUM_ORE) {
                itemStack.decrement(1);
                // Give player a caesium vial
                int slot = user.getInventory().getEmptySlot();
                if (slot == -1) {
                    user.dropItem(ModItems.PIPETTE_OF_CAESIUM);
                } else {
                    user.getInventory().setStack(slot, new ItemStack(ModItems.PIPETTE_OF_CAESIUM));
                }
                // Set to stone
                world.removeBlock(pos, false);
                world.setBlockState(pos, Registry.BLOCK.get(new Identifier("minecraft", "stone")).getDefaultState());

                return TypedActionResult.success(itemStack);
            }

            // water
            if (world.getFluidState(pos).isIn(FluidTags.WATER)) {
                itemStack.decrement(1);
                // Give player a water vial
                int slot = user.getInventory().getEmptySlot();
                if (slot == -1) {
                    user.dropItem(ModItems.PIPETTE_OF_WATER);
                } else {
                    user.getInventory().setStack(slot, new ItemStack(ModItems.PIPETTE_OF_WATER));
                }

                return TypedActionResult.success(itemStack);
            }
        }

        return super.use(world, user, hand);
    }
}
