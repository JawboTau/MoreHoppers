package com.github.jawbotau.goldenhoppers.block;

import com.github.jawbotau.goldenhoppers.block.entity.FilteredHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AncientHopperBlock extends GoldenHopperBlock {
	public AncientHopperBlock(Block.Settings settings) {
		super(settings);
	}
	@Override
	protected void serverTick(World world, BlockPos pos, BlockState state, FilteredHopperBlockEntity entity) {
		//  Hopper calculations are only done if
		//  The cooldown is 0 or less,
		//  so this does not actually lag the game
		HopperBlockEntity.serverTick(world, pos, state, entity);
		HopperBlockEntity.serverTick(world, pos, state, entity);
	}
}
