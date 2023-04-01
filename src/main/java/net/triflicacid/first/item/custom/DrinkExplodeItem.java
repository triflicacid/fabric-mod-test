package net.triflicacid.first.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

public class DrinkExplodeItem extends Item {
    public DrinkExplodeItem(Settings settings) {
        super(settings);
    }

    private void explode(World world, LivingEntity user) {
        // TODO explosion doesn't deal damage??
        world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 8.0f, Explosion.DestructionType.BREAK);
        user.emitGameEvent(GameEvent.EXPLODE);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.emitGameEvent(GameEvent.DRINK);
        explode(world, user);
        stack.decrement(1);
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
