package cx.rain.iadfu.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DFUHolderProvider implements ICapabilitySerializable<CompoundTag> {
    private final IDFUHolder holder = new DFUHolder();
    private final LazyOptional<IDFUHolder> holderOptional = LazyOptional.of(() -> holder);

    public void invalidate() {
        holderOptional.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == IACapabilities.DFU_HOLDER) {
            return holderOptional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        if (holderOptional.isPresent()) {
            return holderOptional.resolve().get().serializeNBT();
        } else {
            return new CompoundTag();
        }
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (holderOptional.isPresent()) {
            holderOptional.resolve().get().deserializeNBT(tag);
        }
    }
}
