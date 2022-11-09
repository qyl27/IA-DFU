package cx.rain.iadfu_test;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(DFUTest.MODID)
@Mod.EventBusSubscriber(modid = DFUTest.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DFUTest {
    public static final String MODID = "iadfutest";
    public static final String NAME = "IA-DFU Test";

    public static final boolean IS_UPDATING = Boolean.getBoolean("iadfu.test.updating");

    private final Logger logger = LoggerFactory.getLogger(NAME);

    public DFUTest() {
        logger.info("Testing IA-DFU.");

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onRegister);

        if (IS_UPDATING) {
            // Todo: test here.

        }
    }

    public void onInitDFU() {
        // Todo.
    }

    public static final ResourceLocation OLD_ITEM_RL = new ResourceLocation(DFUTest.MODID, "old_item");
    public static final ResourceLocation NEW_ITEM_RL = new ResourceLocation(DFUTest.MODID, "new_item");

    public void onRegister(RegisterEvent event) {
        if (!IS_UPDATING) {
            // Old version.
            event.register(Registry.ITEM_REGISTRY, OLD_ITEM_RL, () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
        } else {
            // New version.
            event.register(Registry.ITEM_REGISTRY, NEW_ITEM_RL, () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
        }
    }
}