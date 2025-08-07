package moe.yahvk.moderninline.mixin;

import com.samsthenerd.inline.impl.InlineStyle;
import icyllis.modernui.mc.text.TextLayoutProcessor;
import moe.yahvk.moderninline.HasInlineException;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TextLayoutProcessor.class, remap = false)
public abstract class TextLayoutProcessorMixin {
    @Shadow
    private void reset() {
    }

    // This injection point is the first place where it can be detected that the text contains InlineData without pre-checking.
    @Inject(method = "lambda$new$1", at = @At("HEAD"))
    private void mSequenceBuilder(int index, Style style, int codePoint, CallbackInfoReturnable<Boolean> cir) {
        if (((InlineStyle) style).getInlineData() != null) {
            reset();
            // The use of exceptions here serves two purposes:
            // 1. It allows immediate termination, avoiding further unnecessary layout calculations.
            // 2. It facilitates identifying all places affected by ModernUI, ensuring comprehensive coverage of all cases.
            throw new HasInlineException();
        }
    }
}
