package com.github.jawbotau.goldenhoppers.mixin;

import com.github.jawbotau.goldenhoppers.block.entity.FilteredHopper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.Hopper;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
	@Redirect(method = "insert", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 0))
	private static boolean filterOutput(ItemStack stack, World world, BlockPos pos, BlockState state, Inventory inventory) {
		if (stack.isEmpty()) return true;
		if (inventory instanceof FilteredHopper filteredHopper)
			return !filteredHopper.isAcceptedByFilter(stack);

		return false;
	}

	@Inject(method = "extract(Lnet/minecraft/block/entity/Hopper;Lnet/minecraft/inventory/Inventory;ILnet/minecraft/util/math/Direction;)Z", at = @At("HEAD"), cancellable = true)
	private static void filterInventoryInput(Hopper hopper, Inventory inventory, int slot, Direction side, CallbackInfoReturnable<Boolean> ci) {
		if (hopper instanceof FilteredHopper filteredHopper) {
			if (!filteredHopper.isAcceptedByFilter(inventory.getStack(slot))) {
				ci.setReturnValue(false);
			}
		}
	}

	@Inject(method = "extract(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/ItemEntity;)Z", at = @At("HEAD"), cancellable = true)
	private static void filterItemEntityInput(Inventory inventory, ItemEntity itemEntity, CallbackInfoReturnable<Boolean> ci) {
		if (inventory instanceof FilteredHopper filteredHopper) {
			if (!filteredHopper.isAcceptedByFilter(itemEntity.getStack())) {
				ci.setReturnValue(false);
			}
		}
	}
}
