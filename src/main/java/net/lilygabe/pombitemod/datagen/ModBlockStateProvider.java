package net.lilygabe.pombitemod.datagen;

import net.lilygabe.pombitemod.PombiteMod;
import net.lilygabe.pombitemod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PombiteMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.POMBITELITE_DUST_BLOCK);
        blockWithItem(ModBlocks.RAW_POMBITELITE_BLOCK);
        blockWithItem(ModBlocks.POMBITELITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_POMBITELITE_ORE);

        blockWithItem(ModBlocks.DIRT_COLLAPSER);
        //simpleBlock(ModBlocks.DIRT_COLLAPSER.get(),
        //        new ModelFile.UncheckedModelFile(modLoc("block/dirt_collapser")));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
