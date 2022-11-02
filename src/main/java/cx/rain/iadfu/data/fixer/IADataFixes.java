package cx.rain.iadfu.data.fixer;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import cx.rain.iadfu.IADFU;
import cx.rain.iadfu.api.data.fixer.schema.EmptySchema;
import net.minecraft.SharedConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.util.datafix.DataFixers;

import java.util.HashMap;
import java.util.Map;

public class IADataFixes {
    private final Schema latestVanilla;

    protected Map<String, DataFixerEntry> dataFixers = new HashMap<>();

    private static IADataFixes INSTANCE;

    public static IADataFixes getInstance() {
        if (INSTANCE == null) {
            var schema = DataFixers.getDataFixer()
                    .getSchema(DataFixUtils.makeKey(SharedConstants
                            .getCurrentVersion().getDataVersion().getVersion()));

            if (schema != null) {
                INSTANCE = new IADataFixes(schema);
            } else {
                IADFU.getInstance().getLogger().warn("Failed to initialize. Using no-op implementation.");
                INSTANCE = new NoOpDataFixes();
            }
        }

        return INSTANCE;
    }

    private IADataFixes(Schema latestVanillaSchema) {
        latestVanilla = latestVanillaSchema;
    }

    public record DataFixerEntry(DataFixer dataFixer, int dataVersion) {
    }

    public void registerFixer(String modid, int version, DataFixer fixer) {
        if (dataFixers.containsKey(modid)) {
            IADFU.getInstance().getLogger().error("Mod " + modid + " already registered a data fixer upper.");
            throw new RuntimeException("Mod " + modid + " already registered a data fixer upper.");
        }

        dataFixers.put(modid, new DataFixerEntry(fixer, version));
    }

    public DataFixerEntry getFixerEntry(String modid) {
        return dataFixers.get(modid);
    }

    public Schema createBaseSchema() {
        return new Schema(0, latestVanilla);
    }

    public CompoundTag update(DataFixTypes types, CompoundTag tag, int currentVersion) {
        Dynamic<Tag> current = new Dynamic<>(NbtOps.INSTANCE, tag);

        for (Map.Entry<String, DataFixerEntry> entry : dataFixers.entrySet()) {
            DataFixerEntry dataFixerEntry = entry.getValue();

            current = dataFixerEntry.dataFixer()
                    .update(types.getType(),
                            current,
                            currentVersion, dataFixerEntry.dataVersion());
        }

        return (CompoundTag) current.getValue();
    }

    public static class NoOpDataFixes extends IADataFixes {
        private static final Schema EMPTY_SCHEMA = new EmptySchema(0);

        private NoOpDataFixes() {
            super(EMPTY_SCHEMA);
        }

        @Override
        public Schema createBaseSchema() {
            return EMPTY_SCHEMA;
        }
    }
}
