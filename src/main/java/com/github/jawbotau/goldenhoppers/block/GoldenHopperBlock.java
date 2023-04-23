package com.github.jawbotau.goldenhoppers.block;

import com.github.jawbotau.goldenhoppers.Main;
import com.github.jawbotau.goldenhoppers.block.entity.GoldHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoldenHopperBlock extends FilteredHopperBlock {
	public GoldenHopperBlock(Block.Settings settings) {super(settings);}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new GoldHopperBlockEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return world.isClient() ? null : checkType(type, Main.ANCIENT_HOPPER_BLOCK_ENTITY_TYPE, HopperBlockEntity::serverTick);
	}


}
