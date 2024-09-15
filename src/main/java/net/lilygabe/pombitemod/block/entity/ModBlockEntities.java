package net.lilygabe.pombitemod.block.entity;

import net.lilygabe.pombitemod.PombiteMod;
import net.lilygabe.pombitemod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PombiteMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DirtCollapserBlockEntity>> DIRT_COLLAPSER_BE =
            BLOCK_ENTITIES.register("dirt_collapser_be", () ->
                    BlockEntityType.Builder.of(DirtCollapserBlockEntity::new,
                            ModBlocks.DIRT_COLLAPSER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
