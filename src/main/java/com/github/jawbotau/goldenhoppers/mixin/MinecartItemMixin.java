package com.github.jawbotau.goldenhoppers.mixin;

import com.github.jawbotau.goldenhoppers.item.CustomMinecartItem;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.MinecartItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecartItem.class)
public class MinecartItemMixin {
	@Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target =
"Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;create(Lnet/minecraft/world/World;" +
"DDDLnet/minecraft/entity/vehicle/AbstractMinecartEntity$Type;)Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;"))
	@SuppressWarnings("ConstantConditions")
	private AbstractMinecartEntity createPlacedCustomMinecartEntity(World world, double x, double y, double z, AbstractMinecartEntity.Type type) {
		if ((Object) this instanceof CustomMinecartItem customMinecartItem)
			return customMinecartItem.createEntity(world, x, y, z);
		return AbstractMinecartEntity.create(world, x, y, z, type);
	}
}
