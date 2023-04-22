package com.github.jawbotau.goldenhoppers.entity;

import com.github.jawbotau.goldenhoppers.block.entity.FilteredHopper;
import com.github.jawbotau.goldenhoppers.screen.FilteredHopperScreenHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ItemScatterer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public abstract class FilteredHopperMinecartEntity extends HopperMinecartEntity implements FilteredHopper {
	protected Inventory filterInventory = new SimpleInventory(1);
	public FilteredHopperMinecartEntity(EntityType<FilteredHopperMinecartEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ItemStack getFilter() {
		return this.filterInventory.getStack(0);
	}

	@Override
	public void onBroken(DamageSource source, World world, Entity vehicle) {
		super.onBroken(source, world, vehicle);

		if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			ItemScatterer.spawn(this.getWorld(), this, this.filterInventory);
		}
	}

	@Override
	public ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new FilteredHopperScreenHandler(syncId, playerInventory, this, this.filterInventory);
	}
}
