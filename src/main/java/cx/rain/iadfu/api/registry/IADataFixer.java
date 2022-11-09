package cx.rain.iadfu.api.registry;

import com.mojang.datafixers.DataFixer;

public abstract class IADataFixer {
    public abstract DataFixer buildDataFixer();

    public abstract int getVersion();

    public abstract String getName();
}
