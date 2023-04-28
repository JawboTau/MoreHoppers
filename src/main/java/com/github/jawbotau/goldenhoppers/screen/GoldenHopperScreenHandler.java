package com.github.jawbotau.goldenhoppers.screen;

import com.github.jawbotau.goldenhoppers.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class GoldenHopperScreenHandler extends FilteredHopperScreenHandler {
	protected final Inventory inventory;


	public GoldenHopperScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, Inventory filterInventory) {
		super(syncId, playerInventory, filterInventory, Main.GOLD_SCREEN_HANDLER_TYPE);

		System.out.print('a');
		this.inventory = inventory;
		System.out.print('b');
		ScreenHandler.checkSize(this.inventory, 5);
		System.out.print('c');
		for (int slot = 0; slot < inventory.size(); slot++) {
			System.out.println("inv " + slot);
			this.addSlot(new Slot(this.inventory, slot, slot * 18 + 62, 20));
			System.out.print(slot);
		}
		this.inventory.onOpen(playerInventory.player);
	}

	public GoldenHopperScreenHandler(int syncId, PlayerInventory playerInventory) {
		super(syncId, playerInventory, Main.GOLD_SCREEN_HANDLER_TYPE);
		this.inventory = new SimpleInventory(5);
	}

	@Override
	protected ItemStack quickMove(int index, ItemStack slotStack, ItemStack copy, Slot slot) {
		System.out.println("is " + index + " > " + this.inventory.size() + "?");
		if (index >= this.inventory.size()) return copy;
		System.out.println(index + " is not > than " + this.inventory.size());
		System.out.println("Filter is " + this.filterInventory.size());

		//  Player Inventory
		if (!this.insertItem(slotStack, this.inventory.size() + 1, this.slots.size(), true))
			return ItemStack.EMPTY;
			//  This Inventory
		else if (!this.insertItem(slotStack, 0, this.inventory.size(), false))
			return ItemStack.EMPTY;

		return copy;
	}

	@Override
	public void onClosed(PlayerEntity player) {
		super.onClosed(player);
		this.inventory.onClose(player);
	}
}
