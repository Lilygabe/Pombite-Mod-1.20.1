package net.lilygabe.pombitemod.item;

import net.lilygabe.pombitemod.PombiteMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PombiteMod.MOD_ID);

    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> POMBITE_TAB = CREATIVE_MODE_TABS.register("pombite_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.POMBITE.get()))
                    .title(Component.translatable("creativetab.pombite_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.POMBITE.get());
                        output.accept(ModItems.POMBITELITE_DUST.get());
                        output.accept(ModItems.RAW_POMBITELITE.get());
                    })
                    .build());
}
