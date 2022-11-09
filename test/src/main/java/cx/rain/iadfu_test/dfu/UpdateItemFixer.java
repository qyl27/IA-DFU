package cx.rain.iadfu_test.dfu;

import com.mojang.datafixers.DataFixer;
import cx.rain.iadfu.api.data.fixer.IADataFixerBuilder;
import cx.rain.iadfu.api.data.fixer.schema.IASchemas;
import cx.rain.iadfu.api.registry.IADataFixer;
import cx.rain.iadfu.api.utility.IAFixesHelper;
import cx.rain.iadfu_test.DFUTest;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class UpdateItemFixer extends IADataFixer {
    private final IADataFixerBuilder builder;

    public UpdateItemFixer() {
        builder = new IADataFixerBuilder(1);

        builder.addSchema(0, IASchemas.ROOT_SCHEMA);
        var renameItemSchema = builder.addSchema(1, NamespacedSchema::new);
        IAFixesHelper.addItemRenameFix(builder, renameItemSchema, "Rename old_item to new_item", DFUTest.OLD_ITEM_RL, DFUTest.NEW_ITEM_RL);
    }

    @Override
    public DataFixer buildDataFixer() {
        return builder.build();
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getName() {
        return "Update Item";
    }
}
