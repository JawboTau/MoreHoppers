package com.github.jawbotau.goldenhoppers.screen;

import com.github.jawbotau.goldenhoppers.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public abstract class FilteredHopperScreenHandler extends ScreenHandler {
	protected final Inventory filterInventory;
	protected final Slot[] filterSlots = new Slot[5];

	public FilteredHopperScreenHandler(int syncId, PlayerInventory playerInventory, Inventory filterInventory) {
		super(Main.GOLD_SCREEN_HANDLER_TYPE, syncId);

		// Filter
		this.filterInventory = filterInventory;
		ScreenHandler.checkSize(this.filterInventory, 5);
		for(int i = 0; i < filterSlots.length; i++) {
			this.filterSlots[i] = this.addSlot(new Slot(this.filterInventory, i, i*18+62, 40) {
				@Override
				public int getMaxItemCount() {
					return 1;
				}
			});
		}
		this.filterInventory.onOpen(playerInventory.player);

		// Player inventory
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				this.addSlot(new Slot(playerInventory, column + row * 9 + 9, column * 18 + 8, row * 18 + 51));
			}
		}

		// Hotbar
		for (int column = 0; column < 9; column++) {
			this.addSlot(new Slot(playerInventory, column, column * 18 + 8, 109));
		}
	}

	protected FilteredHopperScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new SimpleInventory(5));
	}

	protected Slot[] getFilterSlots() {
		return this.filterSlots;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.filterInventory.canPlayerUse(player);
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int index) {
		System.out.println(index);
		Slot slot = slots.get(index);
		System.out.println("got slot");
		if (!slot.hasStack()) return ItemStack.EMPTY;

		System.out.println("got stack");

		ItemStack slotStack = slot.getStack();
		System.out.println("doing the big one");

		ItemStack copy = quickMove(index, slotStack, slotStack.copy(), slot);

		System.out.println("quick moving");

		if (slotStack.isEmpty())
			slot.setStack(ItemStack.EMPTY);
		else
			slot.markDirty();

		return copy;
	}

	protected abstract ItemStack quickMove(int index, ItemStack slotStack, ItemStack stack, Slot slot);

	@Override
	public void onClosed(PlayerEntity player) {
		super.onClosed(player);
		this.filterInventory.onClose(player);
	}


}
