package moe.yahvk.moderninline.mixin;

import com.samsthenerd.inline.api.client.InlineClientAPI;
import icyllis.modernui.mc.text.ModernStringSplitter;
import icyllis.modernui.mc.text.ModernTextRenderer;
import icyllis.modernui.mc.text.TextLayoutEngine;
import moe.yahvk.moderninline.HasInlineException;
import moe.yahvk.moderninline.MatchContextController;
import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Font.class)
public abstract class FontRendererMixin {
    @Unique
    private final ModernTextRenderer modernUI_MC$textRenderer =
            TextLayoutEngine.getInstance().getTextRenderer();

    @Redirect(method = "<init>", at = @At(value = "NEW",
            target = "(Lnet/minecraft/client/StringSplitter$WidthProvider;)Lnet/minecraft/client/StringSplitter;"))
    private StringSplitter onNewSplitter(StringSplitter.WidthProvider widthProvider) {
        return new ModernStringSplitter(TextLayoutEngine.getInstance(), widthProvider);
    }

    @Inject(method = "drawInBatch(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;IIZ)I",
            at = @At("HEAD"), cancellable = true)
    public void drawInBatch(String text, float x, float y, int color, boolean dropShadow,
                            Matrix4f matrix, MultiBufferSource source, Font.DisplayMode displayMode,
                            int colorBackground, int packedLight, boolean bidiFlag,
                            CallbackInfoReturnable<Integer> cir) {
        try {
            cir.setReturnValue((int) modernUI_MC$textRenderer.drawText(text, x, y, color, dropShadow, matrix, source,
                    displayMode, colorBackground, packedLight) + (dropShadow ? 1 : 0));
        } catch (HasInlineException ignore) {
        }
    }

    @Inject(method = "drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)I",
            at = @At("HEAD"), cancellable = true)
    public void drawInBatch(Component text, float x, float y, int color, boolean dropShadow,
                            Matrix4f matrix, MultiBufferSource source, Font.DisplayMode displayMode,
                            int colorBackground, int packedLight, CallbackInfoReturnable<Integer> cir) {
        try {
            cir.setReturnValue((int) modernUI_MC$textRenderer.drawText(text, x, y, color, dropShadow, matrix, source,
                    displayMode, colorBackground, packedLight) + (dropShadow ? 1 : 0));
        } catch (HasInlineException ignore) {
        }
    }

    @Inject(method = "drawInBatch(Lnet/minecraft/util/FormattedCharSequence;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)I",
            at = @At("HEAD"), cancellable = true)
    public void drawInBatch(FormattedCharSequence text, float x, float y, int color, boolean dropShadow,
                            Matrix4f matrix, MultiBufferSource source, Font.DisplayMode displayMode,
                            int colorBackground, int packedLight, CallbackInfoReturnable<Integer> cir) {
        try {
            cir.setReturnValue((int) modernUI_MC$textRenderer.drawText(text, x, y, color, dropShadow, matrix, source,
                    displayMode, colorBackground, packedLight) + (dropShadow ? 1 : 0));
        } catch (HasInlineException ignore) {
        }
    }

    @Inject(method = "bidirectionalShaping", at = @At("HEAD"), cancellable = true)
    public void bidirectionalShaping(String text, CallbackInfoReturnable<String> cir) {
        if (((MatchContextController) InlineClientAPI.INSTANCE.getMatched(text)).moderninline$anyMatch()) {
            return;
        }
        cir.setReturnValue(text);
    }

    @Inject(method = "drawInBatch8xOutline", at = @At("HEAD"), cancellable = true)
    public void drawInBatch8xOutline(FormattedCharSequence text, float x, float y, int color, int outlineColor,
                                     Matrix4f matrix, MultiBufferSource source, int packedLight,
                                     CallbackInfo ci) {
        try {
            modernUI_MC$textRenderer.drawText8xOutline(text, x, y, color, outlineColor, matrix, source, packedLight);
            ci.cancel();
        } catch (HasInlineException ignore) {
        }
    }
}
