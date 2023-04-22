package com.github.jawbotau.goldenhoppers.data;

import com.github.jawbotau.goldenhoppers.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class GoldenHoppersBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public GoldenHoppersBlockTagProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(dataOutput, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Main.GOLD_HOPPER_BLOCK);
	}
}
