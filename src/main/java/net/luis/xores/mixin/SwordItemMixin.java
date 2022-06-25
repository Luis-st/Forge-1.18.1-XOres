package net.luis.xores.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.luis.xores.event.xores.MixinEventInfo;
import net.luis.xores.event.xores.XOresEventFactory;
import net.luis.xores.event.xores.MixinEvent.SwordItemMixinEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * {@link Mixin} class for {@link SwordItem}
 * 
 * @author Luis-st
 */

@Mixin(SwordItem.class)
public abstract class SwordItemMixin {
	
	/**
	 * mixin for {@link SwordItem#mineBlock(ItemStack, Level, BlockState, BlockPos, LivingEntity)},<br>
	 * changes the {@link Item} damage behaviour when destroy a insta breakable {@link Block}<br>
	 * in this case the {@link SwordItem} damage value will be increased by 1 instead of 2,<br>
	 * the vanilla logic is in this case never called
	 * 
	 * fires the {@link SwordItemMixinEvent}
	 */
	@Inject(method = "mineBlock", at = @At("HEAD"), cancellable = true)
	public void mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
		if (state.getDestroySpeed(level, pos) != 0.0F) {
			stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		}
		info.setReturnValue(true);
		info.cancel();
		XOresEventFactory.onSwordItemMixin(new MixinEventInfo(SwordItem.class, "mineBlock", info.isCancelled()), stack, level, state, pos, entity);
	}

}
