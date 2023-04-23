package com.github.jawbotau.goldenhoppers;

import com.github.jawbotau.goldenhoppers.screen.FilteredHopperScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.MinecartEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.Identifier;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(Main.GOLD_SCREEN_HANDLER_TYPE, FilteredHopperScreen::new);
		HandledScreens.register(Main.ANCIENT_SCREEN_HANDLER_TYPE, FilteredHopperScreen::new);

		registerMinecart(Main.GOLD_HOPPER_MINECART_ID, Main.GOLD_HOPPER_MINECART_ENTITY_TYPE);
		registerMinecart(Main.ANCIENT_HOPPER_MINECART_ID, Main.ANCIENT_HOPPER_MINECART_ENTITY_TYPE);
	}

	private static <T extends AbstractMinecartEntity> void registerMinecart(Identifier id, EntityType<T> type){
		EntityModelLayer modelLayer = new EntityModelLayer(id, "main");
		EntityModelLayerRegistry.registerModelLayer(modelLayer, MinecartEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(type, context -> new MinecartEntityRenderer<>(context, modelLayer));

	}
}
