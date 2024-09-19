package net.lilygabe.pombitemod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.lilygabe.pombitemod.PombiteMod;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DirtCollapsingRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public DirtCollapsingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if (level.isClientSide())
            return false;

    /*
     *.get(0) -> ingredient at index 0
     *.test(...) -> compares with
     *.getItem(0) -> item in slot 0
     */
    return inputItems.get(0).test(simpleContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DirtCollapsingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "dirt_collapsing";
    }

    public static class Serializer implements RecipeSerializer<DirtCollapsingRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(PombiteMod.MOD_ID, "dirt_collapsing");

        @Override
        public DirtCollapsingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");

            //Change this if there are multiple inputs
            NonNullList<Ingredient> inputs = NonNullList.withSize(1,Ingredient.EMPTY);

            for (int i=0; i<inputs.size(); i++){
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new DirtCollapsingRecipe(inputs,output,resourceLocation);
        }

        @Override
        public @Nullable DirtCollapsingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(friendlyByteBuf.readInt(), Ingredient.EMPTY);

            for (int i=0; i<inputs.size(); i++){
                inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));
            }

            ItemStack output = friendlyByteBuf.readItem();
            return new DirtCollapsingRecipe(inputs,output,resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, DirtCollapsingRecipe dirtCollapserRecipe) {
            friendlyByteBuf.writeInt(dirtCollapserRecipe.inputItems.size());

            for (Ingredient ingredient : dirtCollapserRecipe.getIngredients()){
                ingredient.toNetwork(friendlyByteBuf);
            }

            friendlyByteBuf.writeItemStack(dirtCollapserRecipe.getResultItem(null), false);
        }
    }
}
