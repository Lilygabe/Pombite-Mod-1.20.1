package net.lilygabe.pombitemod.item.custom;

import net.lilygabe.pombitemod.fluo.IFluoStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ArcweaverItem extends Item implements IFluoStorage {
    //TODO: Texture

    private final int fluoColor = 0xF7A1FF;
    private final int burnTime = 200;
    private final int defaultFluo;
    private final int maxFluoStored;

    public ArcweaverItem(Properties properties, int maxFluoStored) {
        super(properties);
        this.maxFluoStored = maxFluoStored;
        defaultFluo = 0;
    }

    public ArcweaverItem(Properties properties, int maxFluoStored, int fluoStored) {
        super(properties);
        this.maxFluoStored = maxFluoStored;
        defaultFluo = fluoStored;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // Get the stored Fluo
        int storedFluo = getFluoStored(stack);
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("it", "CH"));
        String formattedFluo = numberFormat.format(storedFluo);
        String formattedMaxFluo = numberFormat.format(maxFluoStored);

        // Create the tooltip text using Component.literal
        MutableComponent fluoText = Component.literal(formattedFluo+"ƒ").setStyle(Style.EMPTY.withColor(fluoColor))
                .append(Component.literal( "/" + formattedMaxFluo + "ƒ").setStyle(Style.EMPTY.withColor(0XAAAAAA)));

        // Add the tooltip text to the list
        tooltip.add(fluoText);
    }

    @Override
    public int getFluoStored(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("FluoStored")) {
            tag.putInt("FluoStored", defaultFluo);
        }
        return tag.getInt("FluoStored");
    }

    @Override
    public void setFluoStored(ItemStack stack, int fluo) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("FluoStored", Math.min(fluo, maxFluoStored));
        stack.setTag(tag);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        if(getFluoStored(itemStack) > 0)
            return this.burnTime;
        return 0;
    }

    @Override
    public int getMaxFluoStored() {
        return maxFluoStored;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public int receiveFluo(ItemStack stack, int maxReceive, boolean simulate) {
        int currentFluo = getFluoStored(stack);
        int fluoReceived = Math.min(getMaxFluoStored() - currentFluo, maxReceive);
        if (!simulate) {
            setFluoStored(stack, currentFluo + fluoReceived);
        }
        return fluoReceived;
    }

    @Override
    public int extractFluo(ItemStack stack, int maxExtract, boolean simulate) {
        int currentFluo = getFluoStored(stack);
        int fluoExtracted = Math.min(currentFluo, maxExtract);
        if (!simulate) {
            setFluoStored(stack, currentFluo - fluoExtracted);
        }
        return fluoExtracted;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        // Returns the width of the energy bar; it will be scaled to the item icon's size
        return (int) Math.round(13.0D * getFluoStored(stack) / (double) getMaxFluoStored()) ;
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return fluoColor;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return getFluoStored(stack) > 0;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
