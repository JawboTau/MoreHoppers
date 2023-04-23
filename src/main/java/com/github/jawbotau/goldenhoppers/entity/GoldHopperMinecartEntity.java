package com.github.jawbotau.goldenhoppers.entity;

import com.github.jawbotau.goldenhoppers.Main;
import com.github.jawbotau.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public class GoldHopperMinecartEntity extends FilteredHopperMinecartEntity{
	public GoldHopperMinecartEntity(EntityType<FilteredHopperMinecartEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new GoldenHopperScreenHandler(syncId, playerInventory, this, this.filterInventory);
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return Main.GOLD_HOPPER_BLOCK.getDefaultState();
	}

	@Override
	protected Item getItem() {
		return Main.GOLD_HOPPER_MINECART;
	}
}
