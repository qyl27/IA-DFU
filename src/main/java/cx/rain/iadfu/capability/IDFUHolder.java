package cx.rain.iadfu.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Map;

public interface IDFUHolder extends INBTSerializable<CompoundTag> {
    public Map<String, Integer> getModDataMap();
    public boolean hasDfu(String modid);
    public int getDataVersion(String modid);
    public void addDfu(String modid, int version);
    public void update(String modid, int newVersion);
}
