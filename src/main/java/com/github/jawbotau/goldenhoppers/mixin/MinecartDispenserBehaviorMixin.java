package com.github.jawbotau.goldenhoppers.mixin;

import com.github.jawbotau.goldenhoppers.item.CustomMinecartItem;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.item.MinecartItem$1")
public class MinecartDispenserBehaviorMixin {
	@Redirect(method = "dispenseSilently(Lnet/minecraft/util/math/BlockPointer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;create(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/vehicle/AbstractMinecartEntity$Type;)Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;"))
	private AbstractMinecartEntity createDispensedCustomMinecartEntity(World world, double x, double y, double z, MinecartEntity.Type type, BlockPointer pointer, ItemStack stack) {
		if (stack.getItem() instanceof CustomMinecartItem customMinecartItem)
			return customMinecartItem.createEntity(world, x, y, z);
		return AbstractMinecartEntity.create(world, x, y, z, type);
	}
}
