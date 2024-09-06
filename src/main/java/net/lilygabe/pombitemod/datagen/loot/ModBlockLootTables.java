package net.lilygabe.pombitemod.datagen.loot;

import net.lilygabe.pombitemod.block.ModBlocks;
import net.lilygabe.pombitemod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.RAW_POMBITELITE_BLOCK.get());
        this.dropSelf(ModBlocks.POMBITELITE_DUST_BLOCK.get());

        this.add(ModBlocks.POMBITELITE_ORE.get(),
                block -> createOreDrop(ModBlocks.POMBITELITE_ORE.get(), ModItems.RAW_POMBITELITE.get()));
        this.add(ModBlocks.DEEPSLATE_POMBITELITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_POMBITELITE_ORE.get(), ModItems.RAW_POMBITELITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
