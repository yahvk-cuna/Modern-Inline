package moe.yahvk.moderninline;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class Canceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return mixinClassName.equals("icyllis.modernui.mc.text.mixin.MixinFontRenderer");
    }
}
