package com.github.jawbotau.goldenhoppers.mixin;

import com.github.jawbotau.goldenhoppers.block.entity.CustomHopper;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
	@Inject(method = "canRunOnTop", at = @At("HEAD"), cancellable = true)
	private void allowWireOnGoldenHoppers(BlockView world, BlockPos pos, BlockState floor, CallbackInfoReturnable<Boolean> ci) {
		if (floor instanceof CustomHopper) {
			ci.setReturnValue(true);
		}
	}
} 