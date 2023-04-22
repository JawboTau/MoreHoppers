package com.github.jawbotau.goldenhoppers.data;

import com.github.jawbotau.goldenhoppers.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class GoldenHoppersModelProvider extends FabricModelProvider {
	public static final Model HOPPER_MODEL = GoldenHoppersModelProvider.createModel("hopper", TextureKey.PARTICLE, TextureKey.TOP, TextureKey.SIDE, TextureKey.INSIDE);
	public static final Model HOPPER_SIDE_MODEL = GoldenHoppersModelProvider.createModel("hopper_side", "_side", TextureKey.PARTICLE, TextureKey.TOP, TextureKey.SIDE, TextureKey.INSIDE);

	public GoldenHoppersModelProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator modelGenerator) {
		GoldenHoppersModelProvider.registerHopper(Main.GOLD_HOPPER_BLOCK, modelGenerator, Blocks.GOLD_BLOCK);
		GoldenHoppersModelProvider.registerHopper(Main.ANCIENT_HOPPER_BLOCK, modelGenerator, createHopperTextures(new Identifier(Main.MOD_ID, "block/ancient_block")));
	}

	@Override
	public void generateItemModels(ItemModelGenerator modelGenerator) {
		modelGenerator.register(Main.GOLD_HOPPER, Models.GENERATED);
		modelGenerator.register(Main.GOLD_HOPPER_MINECART, Models.GENERATED);
	}

	public static void registerHopper(Block hopper, BlockStateModelGenerator modelGenerator, Block base) {
		registerHopper(hopper, modelGenerator, createHopperTextures(base));
	}
	public static void registerHopper(Block hopper, BlockStateModelGenerator modelGenerator, TextureMap map) {
		Identifier modelId = HOPPER_MODEL.upload(hopper, map, modelGenerator.modelCollector);
		Identifier sideModelId = HOPPER_SIDE_MODEL.upload(hopper, map, modelGenerator.modelCollector);

		BlockStateVariantMap variants = BlockStateVariantMap.create(HopperBlock.FACING)
			.register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
			.register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId))
			.register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90))
			.register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180))
			.register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270));

		modelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(hopper).coordinate(variants));
	}

	public static TextureMap createHopperTextures(Block block) {
		return createHopperTextures(TextureMap.getId(block));
	}
	public static TextureMap createHopperTextures(Identifier id) {
		return new TextureMap()
			.put(TextureKey.PARTICLE, id)
			.put(TextureKey.TOP, id)
			.put(TextureKey.SIDE, id)
			.put(TextureKey.INSIDE, id);
	}

	private static Model createModel(String parent, TextureKey... requiredTextures) {
		Identifier parentId = new Identifier("block/" + parent);
		return new Model(Optional.of(parentId), Optional.empty(), requiredTextures);
	}

	private static Model createModel(String parent, String suffix, TextureKey... requiredTextures) {
		Identifier parentId = new Identifier("block/" + parent);
		return new Model(Optional.of(parentId), Optional.of(suffix), requiredTextures);
	}
}
