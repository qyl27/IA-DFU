package cx.rain.iadfu.api.utility;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.BlockRenameFix;
import net.minecraft.util.datafix.fixes.ItemRenameFix;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

import java.util.Objects;

public class IAFixesHelper {
    public static void addBlockRenameFix(DataFixerBuilder builder, Schema schema, String name,
                                         ResourceLocation oldName, ResourceLocation newName) {
        final String oldNameStr = oldName.toString();
        final String newNameStr = newName.toString();

        builder.addFixer(BlockRenameFix.create(schema, name, (inputName) ->
                Objects.equals(NamespacedSchema.ensureNamespaced(inputName), oldNameStr)
                        ? newNameStr : inputName));
    }

    public static void addItemRenameFix(DataFixerBuilder builder, Schema schema, String name,
                                       ResourceLocation oldName, ResourceLocation newName) {
        final String oldNameStr = oldName.toString();
        final String newNameStr = newName.toString();

        builder.addFixer(ItemRenameFix.create(schema, name, (inputName) ->
                Objects.equals(NamespacedSchema.ensureNamespaced(inputName), oldNameStr)
                        ? newNameStr : inputName));
    }
}
