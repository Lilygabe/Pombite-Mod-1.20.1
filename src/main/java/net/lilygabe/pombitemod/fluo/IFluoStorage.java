package net.lilygabe.pombitemod.fluo;

import net.minecraft.world.item.ItemStack;

public interface IFluoStorage {
    int getFluoStored(ItemStack stack);
    int getMaxFluoStored();
    void setFluoStored(ItemStack stack, int fluo);
    boolean canReceive();
    boolean canExtract();
    int receiveFluo(ItemStack stack, int maxReceive, boolean simulate);
    int extractFluo(ItemStack stack, int maxExtract, boolean simulate);
}