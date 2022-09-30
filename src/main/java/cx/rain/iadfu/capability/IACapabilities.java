package cx.rain.iadfu.capability;

import cx.rain.iadfu.IADFU;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Level;

@Mod.EventBusSubscriber(modid = IADFU.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IACapabilities {
    public static final Capability<IDFUHolder> DFU_HOLDER = CapabilityManager.get(new CapabilityToken<>() { });

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IDFUHolder.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Level> event) {
        var provider = new DFUHolderProvider();
        event.addCapability(new ResourceLocation(IADFU.MODID, "dfu_holder"), provider);
        event.addListener(provider::invalidate);
    }
}
