package com.github.jawbotau.goldenhoppers;

import com.github.jawbotau.goldenhoppers.block.AncientHopperBlock;
import com.github.jawbotau.goldenhoppers.block.GoldenHopperBlock;
import com.github.jawbotau.goldenhoppers.block.entity.AncientHopperBlockEntity;
import com.github.jawbotau.goldenhoppers.block.entity.GoldHopperBlockEntity;
import com.github.jawbotau.goldenhoppers.entity.AncientHopperMinecartEntity;
import com.github.jawbotau.goldenhoppers.entity.FilteredHopperMinecartEntity;
import com.github.jawbotau.goldenhoppers.entity.GoldHopperMinecartEntity;
import com.github.jawbotau.goldenhoppers.item.CustomMinecartItem;
import com.github.jawbotau.goldenhoppers.screen.AncientHopperScreenHandler;
import com.github.jawbotau.goldenhoppers.screen.FilteredHopperScreenHandler;
import com.github.jawbotau.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final String MOD_ID = "goldenhoppers";

	public static final Identifier GOLD_HOPPER_ID = id("golden_hopper"), ANCIENT_HOPPER_ID = id("ancient_hopper"), FILTER_HOPPER_ID = id("filter_hopper");
	public static final Identifier GOLD_HOPPER_MINECART_ID = id("golden_hopper_minecart"), ANCIENT_HOPPER_MINECART_ID = id("ancient_hopper_minecart");

	public static final Block GOLD_HOPPER_BLOCK, ANCIENT_HOPPER_BLOCK;
	public static final Item GOLD_HOPPER, ANCIENT_HOPPER;
	public static final Item GOLD_HOPPER_MINECART, ANCIENT_HOPPER_MINECART;
	public static final BlockEntityType<GoldHopperBlockEntity> GOLD_HOPPER_BLOCK_ENTITY_TYPE;
	public static final BlockEntityType<AncientHopperBlockEntity> ANCIENT_HOPPER_BLOCK_ENTITY_TYPE;
	public static final ScreenHandlerType<FilteredHopperScreenHandler> GOLD_SCREEN_HANDLER_TYPE, ANCIENT_SCREEN_HANDLER_TYPE;
	public static final EntityType<FilteredHopperMinecartEntity> GOLD_HOPPER_MINECART_ENTITY_TYPE, ANCIENT_HOPPER_MINECART_ENTITY_TYPE;

	static {
		GOLD_HOPPER_BLOCK = new GoldenHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).mapColor(MapColor.GOLD));
		ANCIENT_HOPPER_BLOCK = new AncientHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).strength(4.2f, 420f).mapColor(MapColor.BLACK));

		GOLD_HOPPER = new BlockItem(GOLD_HOPPER_BLOCK, new FabricItemSettings());
		ANCIENT_HOPPER = new BlockItem(ANCIENT_HOPPER_BLOCK, new FabricItemSettings());

		GOLD_HOPPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(GoldHopperBlockEntity::new, GOLD_HOPPER_BLOCK).build();
		ANCIENT_HOPPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(AncientHopperBlockEntity::new, ANCIENT_HOPPER_BLOCK).build();


		GOLD_SCREEN_HANDLER_TYPE = new ScreenHandlerType<>(GoldenHopperScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
		ANCIENT_SCREEN_HANDLER_TYPE = new ScreenHandlerType<>(AncientHopperScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

		GOLD_HOPPER_MINECART_ENTITY_TYPE = getHopper(GoldHopperMinecartEntity::new);
		ANCIENT_HOPPER_MINECART_ENTITY_TYPE = getHopper(AncientHopperMinecartEntity::new);

		GOLD_HOPPER_MINECART = new CustomMinecartItem(GOLD_HOPPER_MINECART_ENTITY_TYPE, settings().maxCount(1));
		ANCIENT_HOPPER_MINECART = new CustomMinecartItem(ANCIENT_HOPPER_MINECART_ENTITY_TYPE, settings().maxCount(1));

	}

	static EntityType<FilteredHopperMinecartEntity> getHopper(EntityType.EntityFactory<FilteredHopperMinecartEntity> construct){
		return FabricEntityTypeBuilder
				.create(SpawnGroup.MISC, construct)
				.dimensions(EntityDimensions.changing(0.98f, 0.7f))
				.trackRangeChunks(8)
				.build();
	}

	@Override @SuppressWarnings("UnstableApiUsage")
	public void onInitialize() {
		regBlock(GOLD_HOPPER_ID, GOLD_HOPPER_BLOCK);
		regBlock(ANCIENT_HOPPER_ID, ANCIENT_HOPPER_BLOCK);

		regItem(GOLD_HOPPER_ID, GOLD_HOPPER);
		regItem(ANCIENT_HOPPER_ID, ANCIENT_HOPPER);

		regBlockEntity(GOLD_HOPPER_ID, GOLD_HOPPER_BLOCK_ENTITY_TYPE);
		regBlockEntity(ANCIENT_HOPPER_ID, ANCIENT_HOPPER_BLOCK_ENTITY_TYPE);

		regScreenHandler(GOLD_HOPPER_ID, GOLD_SCREEN_HANDLER_TYPE);
		regScreenHandler(ANCIENT_HOPPER_ID, ANCIENT_SCREEN_HANDLER_TYPE);

		regEntity(GOLD_HOPPER_MINECART_ID, GOLD_HOPPER_MINECART_ENTITY_TYPE);
		regEntity(ANCIENT_HOPPER_MINECART_ID, ANCIENT_HOPPER_MINECART_ENTITY_TYPE);

		regItem(GOLD_HOPPER_MINECART_ID, GOLD_HOPPER_MINECART);
		regItem(ANCIENT_HOPPER_MINECART_ID, ANCIENT_HOPPER_MINECART);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.addAfter(Items.HOPPER, GOLD_HOPPER);
			content.addAfter(Items.HOPPER_MINECART, GOLD_HOPPER_MINECART);
		});
	}



	public static Identifier id(String key){
		return new Identifier(Main.MOD_ID, key);
	}
	private static Item.Settings settings(){
		return new Item.Settings();
	}
	private static <V, T extends V> void register(net.minecraft.registry.Registry<V> registry, Identifier id, T entry) {
		net.minecraft.registry.Registry.register(registry, id, entry);
	}
	private static <T extends Block> void regBlock(Identifier id, T entry) {
		register(Registries.BLOCK, id, entry);
	}
	private static <T extends Item> void regItem(Identifier id, T entry) {
		register(Registries.ITEM, id, entry);
	}
	private static <T extends ScreenHandlerType<?>> void regScreenHandler(Identifier id, T entry) {
		register(Registries.SCREEN_HANDLER, id, entry);
	}
	private static <T extends BlockEntityType<?>> void regBlockEntity(Identifier id, T entry) {
		register(Registries.BLOCK_ENTITY_TYPE, id, entry);
	}
	private static <T extends EntityType<?>> void regEntity(Identifier id, T entry) {
		register(Registries.ENTITY_TYPE, id, entry);
	}
}