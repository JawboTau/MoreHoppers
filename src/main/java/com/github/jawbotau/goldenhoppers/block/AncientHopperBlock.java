package com.github.jawbotau.goldenhoppers.block;

import com.github.jawbotau.goldenhoppers.block.entity.AncientHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AncientHopperBlock extends FilteredHopperBlock {
	public AncientHopperBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new AncientHopperBlockEntity(pos, state);
	}

	@Override
	protected void dropHopperContents(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {return null;}
}
