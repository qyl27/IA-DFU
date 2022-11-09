package cx.rain.iadfu.api.data.fixer.schema;

import com.mojang.datafixers.schemas.Schema;

import java.util.function.BiFunction;

public class IASchemas {
    public static final BiFunction<Integer, Schema, Schema> ROOT_SCHEMA =
            (version, parent) -> new EmptySchema(0);
}
