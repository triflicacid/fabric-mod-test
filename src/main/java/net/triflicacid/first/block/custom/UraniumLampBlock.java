package net.triflicacid.first.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// TODO Idea: glow consistently when uranium block/ore nearby?
public class UraniumLampBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public UraniumLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Toggle on/off
        if (!world.isClient() && hand == Hand.MAIN_HAND && player.getStackInHand(hand).getCount() == 0) {
            world.setBlockState(pos, state.cycle(LIT));
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // *IMPORTANT* register property
        builder.add(LIT);
        super.appendProperties(builder);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Emits a healthy radioactive glow").formatted(Formatting.GREEN));

        super.appendTooltip(stack, world, tooltip, options);
    }
}
