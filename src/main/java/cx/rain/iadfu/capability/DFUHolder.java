package cx.rain.iadfu.capability;

import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public class DFUHolder implements IDFUHolder {
    public static final String TAG_DATA_VERSIONS = "dataVersions";

    protected Map<String, Integer> modDataMap = new HashMap<>();

    @Override
    public Map<String, Integer> getModDataMap() {
        return modDataMap;
    }

    @Override
    public boolean hasDfu(String modid) {
        return modDataMap.containsKey(modid);
    }

    @Override
    public int getDataVersion(String modid) {
        return modDataMap.get(modid);
    }

    @Override
    public void addDfu(String modid, int version) {
        // Todo: qyl27: is it correct?
        update(modid, version);
    }

    @Override
    public void update(String modid, int newVersion) {
        modDataMap.put(modid, newVersion);
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();

        var dataVersions = new CompoundTag();
        for (var entry : modDataMap.entrySet()) {
            dataVersions.putInt(entry.getKey(), entry.getValue());
        }

        tag.put(TAG_DATA_VERSIONS, dataVersions);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        var dataVersions = tag.getCompound(TAG_DATA_VERSIONS);
        for (var modid : dataVersions.getAllKeys()) {
            modDataMap.put(modid, dataVersions.getInt(modid));
        }
    }
}
