package com.github.jawbotau.goldenhoppers.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AncientHopperBlock extends GoldenHopperBlock {
	public AncientHopperBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	protected void dropHopperContents(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {return null;}
}
