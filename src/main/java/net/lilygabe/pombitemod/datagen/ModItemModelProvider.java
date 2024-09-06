package net.lilygabe.pombitemod.datagen;

import net.lilygabe.pombitemod.PombiteMod;
import net.lilygabe.pombitemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PombiteMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ARCWEAVER);
        copyItem(ModItems.ARCWEAVER_FULL, ModItems.ARCWEAVER);

        simpleItem(ModItems.POMBITE);
        simpleItem(ModItems.POMBITELITE_DUST);
        simpleItem(ModItems.RAW_POMBITELITE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PombiteMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder copyItem(RegistryObject<Item> item, RegistryObject<Item> copySource){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PombiteMod.MOD_ID, "item/" + copySource.getId().getPath()));
    }
}
