package com.github.jawbotau.goldenhoppers.entity;

import com.github.jawbotau.goldenhoppers.Main;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class GoldHopperMinecartEntity extends FilteredHopperMinecartEntity{
	public GoldHopperMinecartEntity(EntityType<FilteredHopperMinecartEntity> entityType, World world) {
		super(entityType, world);
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
