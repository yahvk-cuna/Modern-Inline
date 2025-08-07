package moe.yahvk.moderninline.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import icyllis.modernui.mc.text.ModernStringSplitter;
import moe.yahvk.moderninline.HasInlineException;
import net.minecraft.client.StringSplitter;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.BiConsumer;

@Mixin(value = ModernStringSplitter.class, remap = false)
public abstract class ModernStringSplitterMixin extends StringSplitter {
    public ModernStringSplitterMixin(WidthProvider widthProvider) {
        super(widthProvider);
    }

    @WrapMethod(method = "m_92353_(Ljava/lang/String;)F")
    public float stringWidth(String text, Operation<Float> original) {
        try {
            return original.call(text);
        } catch (HasInlineException e) {
            return super.stringWidth(text);
        }
    }

    @WrapMethod(method = "m_92384_(Lnet/minecraft/network/chat/FormattedText;)F")
    public float stringWidth(FormattedText text, Operation<Float> original) {
        try {
            return original.call(text);
        } catch (HasInlineException e) {
            return super.stringWidth(text);
        }
    }

    @WrapMethod(method = "m_92336_(Lnet/minecraft/util/FormattedCharSequence;)F")
    public float stringWidth(FormattedCharSequence text, Operation<Float> original) {
        try {
            return original.call(text);
        } catch (HasInlineException e) {
            return super.stringWidth(text);
        }
    }

    @WrapMethod(method = "m_92360_")
    public int plainIndexAtWidth(String text, int width, Style style, Operation<Integer> original) {
        try {
            return original.call(text, width, style);
        } catch (HasInlineException e) {
            return super.plainIndexAtWidth(text, width, style);
        }
    }

    @WrapMethod(method = "m_92410_")
    public String plainHeadByWidth(String text, int width, Style style, Operation<String> original) {
        try {
            return original.call(text, width, style);
        } catch (HasInlineException e) {
            return super.plainHeadByWidth(text, width, style);
        }
    }

    @WrapMethod(method = "m_92423_")
    public String plainTailByWidth(String text, int width, Style style, Operation<String> original) {
        try {
            return original.call(text, width, style);
        } catch (HasInlineException e) {
            return super.plainTailByWidth(text, width, style);
        }
    }

    @WrapMethod(method = "m_168626_")
    public int formattedIndexByWidth(String text, int width, Style style, Operation<Integer> original) {
        try {
            return original.call(text, width, style);
        } catch (HasInlineException e) {
            return super.formattedIndexByWidth(text, width, style);
        }
    }

    @WrapMethod(method = "m_92386_")
    public Style componentStyleAtWidth(FormattedText text, int width, Operation<Style> original) {
        try {
            return original.call(text, width);
        } catch (HasInlineException e) {
            return super.componentStyleAtWidth(text, width);
        }
    }

    @WrapMethod(method = "m_92338_")
    public Style componentStyleAtWidth(FormattedCharSequence text, int width, Operation<Style> original) {
        try {
            return original.call(text, width);
        } catch (HasInlineException e) {
            return super.componentStyleAtWidth(text, width);
        }
    }

    @WrapMethod(method = "m_168630_")
    public String formattedHeadByWidth(String text, int width, Style style, Operation<String> original) {
        try {
            return original.call(text, width, style);
        } catch (HasInlineException e) {
            return super.formattedHeadByWidth(text, width, style);
        }
    }

    @WrapMethod(method = "m_92389_")
    public FormattedText headByWidth(FormattedText text, int width, Style style, Operation<FormattedText> original) {
        try {
            return original.call(text, width, style);
        } catch (HasInlineException e) {
            return super.headByWidth(text, width, style);
        }
    }

    @WrapMethod(method = "m_92364_")
    public void splitLines(String text, int width, Style style, boolean withEndSpace, LinePosConsumer linePos, Operation<Void> original) {
        try {
            original.call(text, width, style, withEndSpace, linePos);
        } catch (HasInlineException e) {
            super.splitLines(text, width, style, withEndSpace, linePos);
        }
    }

    @WrapMethod(method = "m_92393_")
    public void splitLines(FormattedText text, int width, Style style, BiConsumer<FormattedText, Boolean> consumer, Operation<Void> original) {
        try {
            original.call(text, width, style, consumer);
        } catch (HasInlineException e) {
            super.splitLines(text, width, style, consumer);
        }
    }
}
