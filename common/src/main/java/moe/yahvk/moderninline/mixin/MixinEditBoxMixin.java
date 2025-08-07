package moe.yahvk.moderninline.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import moe.yahvk.moderninline.HasInlineException;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EditBox.class, priority = 1500, remap = false)
public class MixinEditBoxMixin {
    @TargetHandler(
            mixin = "icyllis.modernui.mc.text.mixin.MixinEditBox",
            name = "onRenderWidget"
    )
    @WrapMethod(
            method = "@MixinSquared:Handler"
    )
    private void reduceLogLevel(GuiGraphics gr, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci, Operation<Void> original) {
        try {
            original.call(gr, mouseX, mouseY, deltaTicks, ci);
        } catch (HasInlineException ignored) {
        }
    }
}
