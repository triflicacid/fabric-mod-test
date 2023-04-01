package net.triflicacid.first.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.triflicacid.first.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CaesiumBombBlock extends Block {
    private boolean exploded = false; // make sure - only explode once

    public CaesiumBombBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Ignition requires water pipette
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() == ModItems.PIPETTE_OF_WATER) {
            // Replace with empty pipette
            int slot = player.getInventory().indexOf(stack);
            player.getInventory().removeStack(slot);
            player.getInventory().setStack(slot, new ItemStack(ModItems.PIPETTE));

            // Explode
            // TODO Countdown?
            explode(world, player, pos);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public static void explode(World world, PlayerEntity user, BlockPos pos) {
        float power = (float) Math.floor(Math.random() * 8) + 14;
        world.createExplosion(user, null, new CaesiumBombExplosionBehavior(), pos.getX(), pos.getY(), pos.getZ(), power, false, Explosion.DestructionType.DESTROY);
        user.emitGameEvent(GameEvent.EXPLODE);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (world.isClient || exploded) {
            return;
        }
        // TODO Process explosions every tick to stop lag spikes
        world.removeBlock(pos, false);
        explode(world, null, pos);
        exploded = true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("Incredibly powerful explosive. Pipette some water to trigger.").formatted(Formatting.ITALIC));
        } else {
            tooltip.add(Text.literal("Don't mix with water.").formatted(Formatting.ITALIC));
        }

        super.appendTooltip(stack, world, tooltip, options);
    }
}

class CaesiumBombExplosionBehavior extends ExplosionBehavior {
    @Override
    public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
         return Optional.empty();
    }
}
