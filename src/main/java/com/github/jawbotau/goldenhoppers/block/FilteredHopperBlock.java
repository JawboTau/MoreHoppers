package com.github.jawbotau.goldenhoppers.block;

import com.github.jawbotau.goldenhoppers.block.entity.CustomHopper;
import com.github.jawbotau.goldenhoppers.block.entity.GoldenHopperBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class FilteredHopperBlock extends HopperBlock implements CustomHopper {
	public FilteredHopperBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.isOf(newState.getBlock())) {
			if (world.getBlockEntity(pos) instanceof GoldenHopperBlockEntity filteredHopper) {
				filteredHopper.scatterFilter();

				dropHopperContents(state, world, pos, newState, moved);
			}
		}
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new GoldenHopperBlockEntity(pos, state);
	}

	protected void dropHopperContents(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		super.onStateReplaced(state, world, pos, newState, moved);
	}
}
