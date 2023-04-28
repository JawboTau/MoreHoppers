package com.github.jawbotau.goldenhoppers.block.entity;

import com.github.jawbotau.goldenhoppers.Main;
import com.github.jawbotau.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.stream.IntStream;

public class GoldHopperBlockEntity extends FilteredHopperBlockEntity {
	public GoldHopperBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	protected Text getContainerName() {
		return Text.translatable("container.golden_hopper");
	}

	@Override
	public BlockEntityType<?> getType() {
		return Main.GOLD_HOPPER_BLOCK_ENTITY_TYPE;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new GoldenHopperScreenHandler(syncId, playerInventory, this, filterInventory);
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return IntStream.range(41,46).toArray();
	}
}
