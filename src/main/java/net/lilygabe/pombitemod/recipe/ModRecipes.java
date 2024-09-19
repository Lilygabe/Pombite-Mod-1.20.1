package net.lilygabe.pombitemod.recipe;

import net.lilygabe.pombitemod.PombiteMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PombiteMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DirtCollapsingRecipe>> DIRT_COLLAPSING_SERIALIZER =
            SERIALIZERS.register("dirt_collapsing", () -> DirtCollapsingRecipe.Serializer.INSTANCE);

    public static void register (IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
