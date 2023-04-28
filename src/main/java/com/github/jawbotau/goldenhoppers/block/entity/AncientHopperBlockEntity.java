package com.github.jawbotau.goldenhoppers.block.entity;

import com.github.jawbotau.goldenhoppers.Main;
import com.github.jawbotau.goldenhoppers.screen.AncientHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class AncientHopperBlockEntity extends FilteredHopperBlockEntity{
	public AncientHopperBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	protected Text getContainerName() {
		return Text.translatable("container.ancient_hopper");
	}

	@Override
	public BlockEntityType<?> getType() {
		return Main.ANCIENT_HOPPER_BLOCK_ENTITY_TYPE;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new AncientHopperScreenHandler(syncId, playerInventory, filterInventory);
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return new int[0];
	}
}
