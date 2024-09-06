package net.lilygabe.pombitemod.datagen;

import net.lilygabe.pombitemod.PombiteMod;
import net.lilygabe.pombitemod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PombiteMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.POMBITELITE_DUST_BLOCK.get());

        this.tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.RAW_POMBITELITE_BLOCK.get(),
                        ModBlocks.POMBITELITE_DUST_BLOCK.get());

        this.tag(Tags.Blocks.ORES)
                .add(ModBlocks.POMBITELITE_ORE.get(),
                        ModBlocks.DEEPSLATE_POMBITELITE_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_POMBITELITE_BLOCK.get(),
                    ModBlocks.POMBITELITE_ORE.get(),
                    ModBlocks.DEEPSLATE_POMBITELITE_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_POMBITELITE_BLOCK.get(),
                    ModBlocks.POMBITELITE_ORE.get(),
                    ModBlocks.DEEPSLATE_POMBITELITE_ORE.get());
    }
}
