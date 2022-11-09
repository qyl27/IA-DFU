package cx.rain.iadfu.mixin;

import com.mojang.datafixers.DataFixer;
import cx.rain.iadfu.data.fixer.IADataFixes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.datafix.DataFixTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NbtUtils.class)
public class NbtUtilsMixin {
    @Inject(at = @At("RETURN"),
            method = "update(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/util/datafix/DataFixTypes;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;",
            cancellable = true)
    private void iadfu$update(DataFixer dataFixer, DataFixTypes type,
                              CompoundTag compoundTag, int version,
                              CallbackInfoReturnable<CompoundTag> cir) {
        // Todo
        System.out.println(1);
        var result = IADataFixes.getInstance().update(type, cir.getReturnValue(), version);

        System.out.println(2);
        cir.setReturnValue(result);
    }
}
