package com.github.jawbotau.goldenhoppers.block.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;

public interface FilteredHopper extends CustomHopper{
	ItemStack getFilter();

	default boolean isAcceptedByFilter(ItemStack stack) {
		if (stack == null || stack.isEmpty()) return true;

		// No filter allows any item
		ItemStack filter = this.getFilter();
		if (filter == null || filter.isEmpty()) return true;

		Item filterItem = filter.getItem();
		Item item = stack.getItem();

		if (filterItem == item) {
			// Potions must match
			if (filterItem instanceof PotionItem) {
				return PotionUtil.getPotion(filter) == PotionUtil.getPotion(stack);
			}

			return true;
		}

		return false;
	}
}
