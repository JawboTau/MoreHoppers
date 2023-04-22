package com.github.jawbotau.goldenhoppers.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GoldenHoppersDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		var pack = dataGenerator.createPack();

				pack.addProvider(GoldenHoppersModelProvider::new);
var blockPro =  pack.addProvider(GoldenHoppersBlockTagProvider::new);
				pack.addProvider((output, registries) -> new GoldenHoppersItemTagProvider(output, registries, blockPro));
	}
}