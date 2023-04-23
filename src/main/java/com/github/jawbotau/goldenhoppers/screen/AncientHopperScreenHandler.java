package com.github.jawbotau.goldenhoppers.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class AncientHopperScreenHandler extends FilteredHopperScreenHandler {

	public AncientHopperScreenHandler(int syncId, PlayerInventory playerInventory, Inventory filterInventory) {
		super(syncId, playerInventory, filterInventory);
	}
	public AncientHopperScreenHandler(int syncId, PlayerInventory playerInventory) {
		super(syncId, playerInventory);
	}

	@Override
	protected ItemStack quickMove(int index, ItemStack slotStack, ItemStack copy, Slot slot) {
		if (index > this.filterInventory.size()) return copy;
		//  Player Inventory
		if (!this.insertItem(slotStack, 1, this.slots.size(), true))
			return ItemStack.EMPTY;
		return copy;
	}

	@Override
	public void onClosed(PlayerEntity player) {
		super.onClosed(player);
	}


}
