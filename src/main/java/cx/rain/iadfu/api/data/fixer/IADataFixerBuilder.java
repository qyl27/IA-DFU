package cx.rain.iadfu.api.data.fixer;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.Util;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public class IADataFixerBuilder extends DataFixerBuilder {
    protected final int dataVer;

    public IADataFixerBuilder(int dataVersion) {
        super(dataVersion);
        dataVer = dataVersion;
    }

    public int getDataVersion() {
        return dataVer;
    }

    public DataFixer build(Supplier<Executor> executorGetter) {
        Objects.requireNonNull(executorGetter, "executorGetter cannot be null");
        return switch (SharedConstants.DATAFIXER_OPTIMIZATION_OPTION) {
            case UNINITIALIZED_UNOPTIMIZED, INITIALIZED_UNOPTIMIZED -> buildUnoptimized();
            case UNINITIALIZED_OPTIMIZED, INITIALIZED_OPTIMIZED -> buildOptimized(executorGetter.get());
        };
    }

    public DataFixer build() {
        return build(Util::bootstrapExecutor);
    }
}
