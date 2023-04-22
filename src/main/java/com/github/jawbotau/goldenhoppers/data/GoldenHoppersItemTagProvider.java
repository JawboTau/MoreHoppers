package com.github.jawbotau.goldenhoppers.data;

import com.github.jawbotau.goldenhoppers.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class GoldenHoppersItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public GoldenHoppersItemTagProvider(
			FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, BlockTagProvider blockTagProvider) {
		super(output, completableFuture, blockTagProvider);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
		//  I hate this but if piglins can grab your gold blocks,
		//  why can't they steal your gold hoppers?
		addTo(
			ItemTags.PIGLIN_LOVED,
				Main.GOLD_HOPPER,
				Main.GOLD_HOPPER_MINECART
		);
	}

	private void addTo(TagKey<Item> tagKey, Item... items){
		getOrCreateTagBuilder(tagKey).add(items);
	}
}
