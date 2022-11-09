package cx.rain.iadfu.api.registry;

import cx.rain.iadfu.IADFU;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = IADFU.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DFURegistries {
    public static final RegistryBuilder<IADataFixer> DATA_FIXERS_BUILDER = new RegistryBuilder<IADataFixer>().setName(new ResourceLocation(IADFU.MODID, "dfus")).allowModification();
    private static Supplier<IForgeRegistry<IADataFixer>> DATA_FIXERS;

    @SubscribeEvent
    public static void onRegistryEvent(NewRegistryEvent event) {
        DATA_FIXERS = event.create(DATA_FIXERS_BUILDER);
    }

    public static IForgeRegistry<IADataFixer> getDataFixersRegister() {
        return DATA_FIXERS.get();
    }
}
