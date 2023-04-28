package com.github.jawbotau.goldenhoppers.entity;

import com.github.jawbotau.goldenhoppers.Main;
import com.github.jawbotau.goldenhoppers.screen.AncientHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public class AncientHopperMinecartEntity extends FilteredHopperMinecartEntity{
	public AncientHopperMinecartEntity(EntityType<FilteredHopperMinecartEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new AncientHopperScreenHandler(syncId, playerInventory, this.filterInventory);
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return Main.ANCIENT_HOPPER_BLOCK.getDefaultState();
	}

	@Override
	protected Item getItem() {
		return Main.ANCIENT_HOPPER_MINECART;
	}
}
