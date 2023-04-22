package com.github.jawbotau.goldenhoppers.block.entity;

import com.github.jawbotau.goldenhoppers.Main;
import com.github.jawbotau.goldenhoppers.screen.FilteredHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.stream.IntStream;

public class FilteredHopperBlockEntity extends HopperBlockEntity implements FilteredHopper, SidedInventory {
	private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 5).toArray();

	private final Inventory filterInventory = new SimpleInventory(1);

	public FilteredHopperBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public ItemStack getFilter() {
		return filterInventory.getStack(0);
	}

	public void scatterFilter() {
		ItemScatterer.spawn(getWorld(), getPos(), filterInventory);
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return AVAILABLE_SLOTS;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, Direction direction) {
		return isAcceptedByFilter(stack);
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	protected Text getContainerName() {
		return Text.translatable("container.golden_hopper");
	}

	@Override
	public void readNbt(NbtCompound tag) {
		super.readNbt(tag);
		filterInventory.setStack(0, ItemStack.fromNbt(tag.getCompound("Filter")));
	}

	@Override
	public void writeNbt(NbtCompound tag) {
		tag.put("Filter", filterInventory.getStack(0).writeNbt(new NbtCompound()));
		super.writeNbt(tag);
	}

	@Override
	public BlockEntityType<?> getType() {
		return Main.FILTER_HOPPER_BLOCK_ENTITY_TYPE;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new FilteredHopperScreenHandler(syncId, playerInventory, this, filterInventory);
	}
}
