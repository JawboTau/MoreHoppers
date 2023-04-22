package com.github.jawbotau.goldenhoppers;

import com.github.jawbotau.goldenhoppers.block.AncientHopperBlock;
import com.github.jawbotau.goldenhoppers.block.GoldenHopperBlock;
import com.github.jawbotau.goldenhoppers.block.entity.FilteredHopperBlockEntity;
import com.github.jawbotau.goldenhoppers.entity.AncientHopperMinecartEntity;
import com.github.jawbotau.goldenhoppers.entity.FilteredHopperMinecartEntity;
import com.github.jawbotau.goldenhoppers.entity.GoldHopperMinecartEntity;
import com.github.jawbotau.goldenhoppers.item.CustomMinecartItem;
import com.github.jawbotau.goldenhoppers.screen.FilteredHopperScreenHandler;
import net.fabricmc.api.ModInitializer;
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

	public static final Identifier GOLD_HOPPER_ID = id("golden_hopper"), ANCIENT_HOPPER_ID = id("ancient_hopper");
	public static final Identifier GOLD_HOPPER_MINECART_ID = id("golden_hopper_minecart"), ANCIENT_HOPPER_MINECART_ID = id("ancient_hopper_minecart");

	public static final Block GOLD_HOPPER_BLOCK, ANCIENT_HOPPER_BLOCK;
	public static final Item GOLD_HOPPER, ANCIENT_HOPPER;
	public static final Item GOLD_HOPPER_MINECART, ANCIENT_HOPPER_MINECART;
	public static final BlockEntityType<FilteredHopperBlockEntity> FILTER_HOPPER_BLOCK_ENTITY_TYPE, ANCIENT_HOPPER_BLOCK_ENTITY_TYPE;
	public static final ScreenHandlerType<FilteredHopperScreenHandler> FILTER_HOPPER_SCREEN_HANDLER_SCREEN_HANDLER_TYPE;
	public static final EntityType<FilteredHopperMinecartEntity> GOLD_HOPPER_MINECART_ENTITY_TYPE, ANCIENT_HOPPER_MINECART_ENTITY_TYPE;

	static {
		GOLD_HOPPER_BLOCK = new GoldenHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).mapColor(MapColor.GOLD));
		ANCIENT_HOPPER_BLOCK = new AncientHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).strength(4.2f, 420f).mapColor(MapColor.BLACK));

		GOLD_HOPPER = new BlockItem(GOLD_HOPPER_BLOCK, new Item.Settings());
		ANCIENT_HOPPER = new BlockItem(ANCIENT_HOPPER_BLOCK, new Item.Settings());

		FILTER_HOPPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(FilteredHopperBlockEntity::new, GOLD_HOPPER_BLOCK).build();
		ANCIENT_HOPPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(FilteredHopperBlockEntity::new, ANCIENT_HOPPER_BLOCK).build();


		FILTER_HOPPER_SCREEN_HANDLER_SCREEN_HANDLER_TYPE = new ScreenHandlerType<>(FilteredHopperScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
		GOLD_HOPPER_MINECART_ENTITY_TYPE = getHopper(GoldHopperMinecartEntity::new);
		ANCIENT_HOPPER_MINECART_ENTITY_TYPE = getHopper(AncientHopperMinecartEntity::new);
		ANCIENT_HOPPER_MINECART = new CustomMinecartItem(GOLD_HOPPER_MINECART_ENTITY_TYPE, settings().maxCount(1));
		GOLD_HOPPER_MINECART = new CustomMinecartItem(ANCIENT_HOPPER_MINECART_ENTITY_TYPE, settings().maxCount(1));
	}

	static EntityType<FilteredHopperMinecartEntity> getHopper(EntityType.EntityFactory<FilteredHopperMinecartEntity> construct){
		EntityType.EntityFactory<FilteredHopperMinecartEntity> e = GoldHopperMinecartEntity::new;
		return FabricEntityTypeBuilder
				.create(SpawnGroup.MISC, e)
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

		regBlockEntity(GOLD_HOPPER_ID, FILTER_HOPPER_BLOCK_ENTITY_TYPE);
		regScreenHandler(GOLD_HOPPER_ID, FILTER_HOPPER_SCREEN_HANDLER_SCREEN_HANDLER_TYPE);

		regEntity(GOLD_HOPPER_ID, GOLD_HOPPER_MINECART_ENTITY_TYPE);
		regEntity(ANCIENT_HOPPER_ID, ANCIENT_HOPPER_MINECART_ENTITY_TYPE);

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