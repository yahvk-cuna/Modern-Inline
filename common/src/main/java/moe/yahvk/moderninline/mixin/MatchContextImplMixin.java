package moe.yahvk.moderninline.mixin;

import com.samsthenerd.inline.api.matching.InlineMatch;
import com.samsthenerd.inline.impl.MatchContextImpl;
import moe.yahvk.moderninline.MatchContextController;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.TreeMap;

@Mixin(value = MatchContextImpl.class, remap = false)
public class MatchContextImplMixin implements MatchContextController {
    @Shadow
    @Final
    private TreeMap<Integer, InlineMatch> matchMap;

    @Unique
    @Override
    public boolean moderninline$anyMatch() {
        return !matchMap.isEmpty();
    }
}
