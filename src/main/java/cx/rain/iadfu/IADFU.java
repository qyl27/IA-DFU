package cx.rain.iadfu;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(IADFU.MODID)
public class IADFU {
    public static final String MODID = "iadfu";
    public static final String NAME = "IA-DFU";
    public static final String VERSION = "@version@";
    public static final int DATA_VERSION = Integer.parseInt("@dataVersion@");

    private static IADFU INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(NAME);

    public IADFU() {
        INSTANCE = this;
    }

    public static IADFU getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return logger;
    }
}
