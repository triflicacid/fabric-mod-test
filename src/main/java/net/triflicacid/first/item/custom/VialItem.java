package net.triflicacid.first.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.triflicacid.first.block.ModBlocks;
import net.triflicacid.first.block.custom.RapeseedCropBlock;
import net.triflicacid.first.item.ModItems;

public class VialItem extends Item {
    public VialItem(Settings settings) {
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

            // water
            if (world.getFluidState(pos).isIn(FluidTags.WATER)) {
                // Give player a water vial
                int slot = user.getInventory().getEmptySlot();
                if (slot == -1) {
                    user.dropItem(ModItems.VIAL_OF_WATER);
                } else {
                    user.getInventory().setStack(slot, new ItemStack(ModItems.VIAL_OF_WATER));
                }
                itemStack.decrement(1);

                return TypedActionResult.success(itemStack);
            }

            // rapeseed crop
            if (world.getBlockState(pos) == ModBlocks.RAPESEED_CROP.getDefaultState().with(RapeseedCropBlock.AGE, RapeseedCropBlock.MAX_AGE)) {
                // Give player a rapeseed vial
                int slot = user.getInventory().getEmptySlot();
                if (slot == -1) {
                    user.dropItem(ModItems.VIAL_OF_RAPESEED_OIL);
                } else {
                    user.getInventory().setStack(slot, new ItemStack(ModItems.VIAL_OF_RAPESEED_OIL));
                }
                // Consume pipette
                itemStack.decrement(1);

                return TypedActionResult.success(itemStack);
            }
        }

        return super.use(world, user, hand);
    }
}
