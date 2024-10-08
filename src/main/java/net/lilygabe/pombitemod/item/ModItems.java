package net.lilygabe.pombitemod.item;

import net.lilygabe.pombitemod.PombiteMod;
import net.lilygabe.pombitemod.item.custom.ArcweaverItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PombiteMod.MOD_ID);

    public static final RegistryObject<Item> POMBITE = ITEMS.register("pombite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POMBITELITE_DUST = ITEMS.register("pombitelite_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_POMBITELITE = ITEMS.register("raw_pombitelite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARCWEAVER = ITEMS.register("arcweaver",
            () -> new ArcweaverItem(new Item.Properties(), 10_000));
    public static final RegistryObject<Item> ARCWEAVER_FULL = ITEMS.register("arcweaver_full",
            () -> new ArcweaverItem(new Item.Properties(), 10_000,10_000));

    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
