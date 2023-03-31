package net.triflicacid.first.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.triflicacid.first.block.ModBlocks;

public class DiceItem extends Item {
    public DiceItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            // Output a random number
            user.sendMessage(Text.literal("You rolled a " + getRandomNumber()));

            // Add a cooldown (prevent spamming) for 1 second
            ((PlayerEntity) user).getItemCooldownManager().set(this, 20);
        }

        return stack;
    }

    private int getRandomNumber() {
        return (int) (Math.random() * 6) + 1;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        // Server?
        if (!world.isClient() && context.getHand() == Hand.MAIN_HAND) {
            // context.getPlayer().sendMessage(Text.of("Use On Block"));

            // Get position to place block at
            BlockPos pos = context.getBlockPos().offset(context.getSide());
            ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
            if (world.canSetBlock(pos) && stack.getCount() > 0) {
                // Place dice block
                world.setBlockState(pos, ModBlocks.DICE_BLOCK.getDefaultState());
                world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, pos);

                // Reduce item count
                if (!context.getPlayer().getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 1; // near-immediate
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
