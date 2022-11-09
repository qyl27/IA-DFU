package cx.rain.iadfu_test.dfu;

import cx.rain.iadfu.api.registry.IADFURegistries;
import cx.rain.iadfu.api.registry.IADataFixer;
import cx.rain.iadfu_test.DFUTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDFU {
    public static final DeferredRegister<IADataFixer> DATA_FIXERS = DeferredRegister.create(IADFURegistries.getDataFixersRegistry(), DFUTest.MODID);

    public static void register(IEventBus bus) {
        DATA_FIXERS.register(bus);
    }

    public static final RegistryObject<IADataFixer> UPDATE_ITEM = DATA_FIXERS.register("update_item", UpdateItemFixer::new);
}
