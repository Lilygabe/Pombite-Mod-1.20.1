package net.lilygabe.pombitemod.mixin;

import net.lilygabe.pombitemod.item.custom.ArcweaverItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class BatteryFurnaceMixin {

    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V", ordinal = 0))
    private static void redirectShrink(ItemStack itemstack, int amount) {

        if (itemstack.getItem() instanceof ArcweaverItem) {
            ((ArcweaverItem) itemstack.getItem()).extractFluo(itemstack,100,false);
            return;
        }
        // otherwise act as normal
        itemstack.shrink(amount);
    }

}