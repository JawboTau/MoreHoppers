package com.github.jawbotau.goldenhoppers.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public abstract class FilteredHopperBlockEntity extends HopperBlockEntity implements FilteredHopper, SidedInventory {
	protected final Inventory filterInventory = new SimpleInventory(5);

	public FilteredHopperBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public Inventory getFilters() {
		return filterInventory;
	}

	public void scatterFilter() {
		ItemScatterer.spawn(getWorld(), getPos(), filterInventory);
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
	public abstract BlockEntityType<?> getType();
}
